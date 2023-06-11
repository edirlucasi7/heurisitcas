package com.deveficiente.heuristicas.templatesefuncoes.execucaoassincrona.v1;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RecebeRespostaExercicioController {

	private RespostaRepository respostaRepository;
	private ExercicioRepository exercicioRepository;
	private IntegracaoTypeForm integracaoTypeForm;
	private ExecutaComTransacao executaComTransacao;
	private ExecutaAsync executaAsync;
	private SubmeteRespostaParaAnalise submeteRespostaParaAnalise;

	public RecebeRespostaExercicioController(
			RespostaRepository respostaRepository,
			ExercicioRepository exercicioRepository,
			IntegracaoTypeForm integracaoTypeForm, ExecutaComTransacao executaComTransacao, ExecutaAsync executaAsync, SubmeteRespostaParaAnalise submeteRespostaParaAnalise) {
		super();
		this.respostaRepository = respostaRepository;
		this.exercicioRepository = exercicioRepository;
		this.integracaoTypeForm = integracaoTypeForm;
		this.executaComTransacao = executaComTransacao;
		this.executaAsync = executaAsync;
		this.submeteRespostaParaAnalise = submeteRespostaParaAnalise;
	}

	@PostMapping("/recebe-resposta/v1")
	@Transactional
	public ResponseEntity<?> executa(Aluno alunoLogado,
			NovaRespostaRequest request) {

		if (integracaoTypeForm.verificaExistencia(request.idExercicio,
				alunoLogado.getEmail())) {
			Resposta novaResposta = request.toResposta(exercicioRepository);
			
			/*
			 * Agora, depois que salva uma nova resposta, é necessário mandar
			 * a resposta para análise para o sistema de correção automática.
			 * 
			 * Já temos a interface + classe que disponibilizam tal comportamento. 
			 * Olhe a SubmeteRespostaParaAnaliseComAmazonSQS.
			 * 
			 * Um detalhe é que tal envio deve ser feito de maneira assíncrona. O retorno
			 * deste método não deve ficar travado esperando o retorno do sistema de 
			 * análise. 
			 * 
			 */			
			
			ResponseEntity<?> statusRetorno = executaComTransacao.comRetorno(() -> {
				System.out.println("Salvando resposta e definindo retorno");
				respostaRepository.salva(novaResposta);
				return ResponseEntity.ok("Resposta salva com sucesso");
			});

			this.executaAsync.semRetorno(() ->
					this.submeteRespostaParaAnalise.envia(novaResposta));


			return statusRetorno;
		}

		return ResponseEntity.notFound().build();
	}

	public static void main(String[] args) {

		RespostaRepository respostaRepository = new RespostaRepository();
		ExercicioRepository exercicioRepository = new ExercicioRepository();
		IntegracaoTypeForm integracaoTypeForm = new IntegracaoTypeForm();
		ExecutaComTransacao executaComTransacao = new ExecutaComTransacao();
		ExecutaAsync executaAsync = new ExecutaAsync();
		AmazonSQS amazonSQS = new AmazonSQS();
		SubmeteRespostaParaAnalise submeteRespostaParaAnalise = new SubmeteRespostaParaAnaliseComAmazonSQS(amazonSQS);

		
		RecebeRespostaExercicioController controller = new RecebeRespostaExercicioController(respostaRepository,
				exercicioRepository, integracaoTypeForm,executaComTransacao, executaAsync, submeteRespostaParaAnalise);
		
		Aluno alunoLogado = new Aluno("teste@deveficiente.com");
		NovaRespostaRequest request = new NovaRespostaRequest(1l,
				"texto da resposta");
		
		ResponseEntity<?> response = controller.executa(alunoLogado, request);
		System.out.println(response.getStatusCodeValue());
	}
}
