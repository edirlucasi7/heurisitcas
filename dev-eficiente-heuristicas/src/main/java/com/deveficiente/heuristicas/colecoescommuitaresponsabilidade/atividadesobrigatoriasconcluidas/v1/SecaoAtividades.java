package com.deveficiente.heuristicas.colecoescommuitaresponsabilidade.atividadesobrigatoriasconcluidas.v1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecaoAtividades implements Comparable<SecaoAtividades>{

	private String titulo;
	private SortedSet<Atividade> atividades = new TreeSet<>();
	private int ordem;

	public SecaoAtividades(String titulo,int ordem,List<Atividade> novasAtividades) {
		super();
		this.titulo = titulo;
		this.ordem = ordem;
		novasAtividades.stream().forEach(this.atividades::add);
	}

	public int verificaAtividadesObrigatorias() {
		return (int) this.atividades.stream().filter(Atividade::verificaAtividadesObrigatorias).count();
	}

	public int retornaQuantidadeAtividades() {
		return this.atividades.size();
	}
	
	@Override
	public int compareTo(SecaoAtividades outra) {
		return this.ordem - outra.ordem;
	}

	public SortedSet<Atividade> getAtividades() {
		return this.atividades;
	}

	public List<Resposta> respostasDeterminadoAluno(Aluno aluno) {
		return this.atividades.stream()
				.flatMap(atividade -> atividade.respostasDeterminadoAluno(aluno).stream())
				.collect(Collectors.toList());
	}
}
