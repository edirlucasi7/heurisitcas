package com.deveficiente.heuristicas.coesaobasica.validarequest.v1;

import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.Optional;

public class NovoClienteRequest {

	private String nomePais;
	private String nomeEstado;

	public NovoClienteRequest(String nomePais) {
		super();
		Assert.notNull(nomePais,"O nome do país é obrigatório");
		this.nomePais = nomePais;
	}
	
	public void setEstado(String nomeEstado) {
		//estado é opcional
		this.nomeEstado = nomeEstado;
	}


	public Optional<String> getEstado() {
		return Optional.ofNullable(this.nomeEstado);
	}

	public String getNomePais() {
		return this.nomePais;
	}
}
