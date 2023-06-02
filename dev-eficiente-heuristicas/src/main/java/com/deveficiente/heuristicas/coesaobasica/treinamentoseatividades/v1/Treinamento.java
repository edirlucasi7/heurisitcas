package com.deveficiente.heuristicas.coesaobasica.treinamentoseatividades.v1;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Treinamento {

	private String titulo;
	private List<String> titulosAtividades = new ArrayList<>();

	public Treinamento(String titulo) {
		super();
		this.titulo = titulo;
	}

	public static void main(String[] args) {
		Treinamento testeDeFogo = new Treinamento("teste de fogo para você");
		
		/*
		 * Primeiro passo é vocé implementar a adição de titulos de atividades
		 * dentro do treinamento. Como você faria?
		 */

		testeDeFogo.addTituloAtividade("Atividade 1");
		testeDeFogo.addTituloAtividade("Atividade 2");
		testeDeFogo.addTituloAtividade("Atividade 3");

		/*
		 * Uma vez que você implementou a adição de titulos de atividades,
		 * é necessário que você possibilite a descoberta da posicao do titulo
		 * da atividade dentro do treinamento. 
		 */

		Optional<Integer> posicaoAtividade1 = testeDeFogo.posicaoTituloAtividade("Atividade 1");
		Optional<Integer> posicaoAtividade2 = testeDeFogo.posicaoTituloAtividade("Atividade 2");
		Optional<Integer> posicaoAtividade3 = testeDeFogo.posicaoTituloAtividade("Atividade 3");
		System.out.println("A posição da Atividadade 1 é " + posicaoAtividade1);
		System.out.println("A posição da Atividadade 2 é " + posicaoAtividade2);
		System.out.println("A posição da Atividadade 3 é " + posicaoAtividade3);
	}

	private Optional<Integer> posicaoTituloAtividade(String tituloAtividade) {
		int posicao = this.titulosAtividades.indexOf(tituloAtividade) + 1;
		if (posicao > 0) {
			return Optional.of(posicao);
		}
		return Optional.empty();
	}

	private void addTituloAtividade(String titulo) {
		this.titulosAtividades.add(titulo);
	}
}
