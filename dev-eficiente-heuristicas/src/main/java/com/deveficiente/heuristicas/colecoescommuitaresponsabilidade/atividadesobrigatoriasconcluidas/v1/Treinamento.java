package com.deveficiente.heuristicas.colecoescommuitaresponsabilidade.atividadesobrigatoriasconcluidas.v1;

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
		return this.secoes.stream().mapToInt(SecaoAtividades::verificaAtividadesObrigatorias).sum();
	}
	
	public int calculaQuantasObrigatoriasForamFinalizadas(Aluno aluno) {
		return (int) this.secoes.stream().flatMap(
				secao -> secao.respostasDeterminadoAluno(aluno).stream())
						.filter(resposta -> resposta.isObrigatoria()).count();
	}
	
	public BigDecimal calculaPercentualDeAtividadesObrigatorias() {
		return BigDecimal.valueOf(calculaQuantidadeAtividadesObrigatorias())
				.divide(BigDecimal.valueOf(calculaQuantidadeDeAtividades()), 2, BigDecimal.ROUND_HALF_UP);
	}

	private int calculaQuantidadeDeAtividades() {
		return this.secoes.stream()
				.mapToInt(SecaoAtividades::retornaQuantidadeAtividades)
				.sum();
	}
	
	public static void main(String[] args) {
		Aluno aluno1 = new Aluno("aluno1@email.com");
		Aluno aluno2 = new Aluno("aluno2@email.com");
		AtividadeRepository atividadeRepository = new AtividadeRepository();
		
		List<Atividade> atividades = new ArrayList<>();
		Atividade atividade1 = new Atividade("t1", 0, TipoAtividade.OBRIGATORIA);
		atividadeRepository.save(atividade1);
		atividades.add(atividade1);
		atividade1.adicionaResposta(new Resposta(atividade1, aluno1));
		atividade1.adicionaResposta(new Resposta(atividade1, aluno2));
		
		Atividade atividade2 = new Atividade("t2", 1, TipoAtividade.OBRIGATORIA);
		atividadeRepository.save(atividade2);
		atividade2.adicionaResposta(new Resposta(atividade2, aluno1));
		atividades.add(atividade2);
		
		Atividade atividade3 = new Atividade("t3", 2, TipoAtividade.OBRIGATORIA);
		atividadeRepository.save(atividade3);
		atividade3.adicionaResposta(new Resposta(atividade2, aluno1));
		atividades.add(atividade3);

		SecaoAtividades secaoAtividades = new SecaoAtividades("titulo",1,atividades);

		Treinamento treinamento = new Treinamento("titulo do treinamento", List.of(secaoAtividades));

		int totalDeAtividadesObrigatorias = treinamento.calculaQuantidadeAtividadesObrigatorias();
		System.out.println(totalDeAtividadesObrigatorias);

		int quantidadeDeAtividadesObrigatoriasForamFinalizadas = treinamento.calculaQuantasObrigatoriasForamFinalizadas(aluno1);
		System.out.println(quantidadeDeAtividadesObrigatoriasForamFinalizadas);

		BigDecimal percentualAtividadesObrigatorias = treinamento.calculaPercentualDeAtividadesObrigatorias();
		System.out.println(percentualAtividadesObrigatorias);
	}
}
