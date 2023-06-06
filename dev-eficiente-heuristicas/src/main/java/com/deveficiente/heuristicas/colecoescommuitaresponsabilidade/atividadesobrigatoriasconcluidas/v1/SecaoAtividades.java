package com.deveficiente.heuristicas.colecoescommuitaresponsabilidade.atividadesobrigatoriasconcluidas.v1;

import java.util.*;
import java.util.stream.Collectors;

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
	
	@Override
	public int compareTo(SecaoAtividades outra) {
		return this.ordem - outra.ordem;
	}

	public SortedSet<Atividade> getAtividades() {
		return this.atividades;
	}
}
