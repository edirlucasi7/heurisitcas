package com.deveficiente.heuristicas.enumsricas.tipoatividade.v1;

import java.math.BigDecimal;
import java.util.*;

public class Treinamento {

	private String titulo;
	private SortedSet<SecaoAtividades> secoes = new TreeSet<>();

	public Treinamento(String titulo, List<SecaoAtividades> secoes) {
		super();
		this.titulo = titulo;
		secoes.forEach(this.secoes :: add);
	}
	
	public int calculaQuantidadeAtividadesObrigatorias() {
		return this.secoes.stream()
				.mapToInt(SecaoAtividades::calculaQuantidadeAtividadesObrigatorias)
				.sum();
	}
	
	public int calculaQuantasObrigatoriasForamFinalizadas(Aluno aluno) {
		return this.secoes.stream()
				.mapToInt(secao -> secao.calculaQuantasObrigatoriasForamFinalizadas(aluno))
				.sum();
	}
	
	public int calculaQuantidadeAtividadesNaoObrigatorias() {
		return 0;
	}
	
	public BigDecimal calculaPercentualDeAtividadesObrigatorias() {
		return BigDecimal.ZERO;
	}
	
	public static void main(String[] args) {
		Aluno aluno1 = new Aluno("aluno1@email.com");
		Aluno aluno2 = new Aluno("aluno2@email.com");
		AtividadeRepository atividadeRepository = new AtividadeRepository();
		
		List<Atividade> atividades = new ArrayList<>();
		Atividade atividade1 = new Atividade("t1", 0,TipoAtividade.EXEMPLO_TRABALHADO);
		atividadeRepository.save(atividade1);
		atividades.add(atividade1);
		atividade1.adicionaResposta(new Resposta(atividade1, aluno1));
		atividade1.adicionaResposta(new Resposta(atividade1, aluno2));
		
		Atividade atividade2 = new Atividade("t2", 1,TipoAtividade.IMITACAO);
		atividadeRepository.save(atividade2);
		atividade2.adicionaResposta(new Resposta(atividade2, aluno1));
		atividades.add(atividade2);

		Atividade atividade3 = new Atividade("t3", 2,TipoAtividade.CONVENCIONAL);
		atividadeRepository.save(atividade3);
		atividades.add(atividade3);

		Resposta resposta1 = new Resposta(atividade3, aluno1);
		atividade3.adicionaResposta(resposta1);

		SecaoAtividades secaoAtividades = new SecaoAtividades("titulo",1,atividades);

		Treinamento treinamento = new Treinamento("titulo do treinamento", List.of(secaoAtividades));
		System.out.println(treinamento.calculaQuantidadeAtividadesObrigatorias());
		System.out.println(treinamento.calculaQuantasObrigatoriasForamFinalizadas(aluno1));
	}
}
