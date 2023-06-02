package com.deveficiente.heuristicas.coesaobasica.validarequest.v1;

public class Estado {

	private String nome;
	private Pais pais;

	public Estado(String nome, Pais pais) {
		super();
		this.nome = nome;
		this.pais = pais;
	}

	public boolean verificaSePertenceAoPais(Pais pais) {
		return this.pais.equals(pais);
	}

	public Pais getPais() {
		return this.pais;
	}
}
