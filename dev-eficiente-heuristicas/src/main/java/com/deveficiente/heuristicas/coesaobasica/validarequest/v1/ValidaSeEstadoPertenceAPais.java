package com.deveficiente.heuristicas.coesaobasica.validarequest.v1;

import java.util.List;

public class ValidaSeEstadoPertenceAPais {
	
	/*
	 * Existem dois países cadastrados com os seguintes nomes:
	 * 
	 * 1) Brasil
	 * 2) Estados Unidos
	 * 
	 * Existem também dois estados pré cadastrados para cada país
	 * 
	 * 1) Bahia 
	 * 2) Californina
	 */
	private BancoDeDadosPaisesEEstados bancoDeDadosPaisesEEstados;
	
	public ValidaSeEstadoPertenceAPais(
			BancoDeDadosPaisesEEstados bancoDeDadosPaisesEEstados) {
		super();
		this.bancoDeDadosPaisesEEstados = bancoDeDadosPaisesEEstados;
	}

	/**
	 * 
	 * @return lista com mensagens de erros
	 */
	public List<String> valida(NovoClienteRequest request) {
		Estado estado = bancoDeDadosPaisesEEstados.buscaEstadoPeloNome(request.getEstado());
		Pais pais = bancoDeDadosPaisesEEstados.buscaPaisPeloNome(request.getNomePais());

		estado.verificaSePertenceAoPais(pais);

		return List.of("O país não pertence ao estado passado!");
	}
	
	public static void main(String[] args) {
		ValidaSeEstadoPertenceAPais validador = new ValidaSeEstadoPertenceAPais(
				new BancoDeDadosPaisesEEstados());
		
		/*
		 * Aqui fique a vontade para brincar com os valores para testar seu validador
		 */
		NovoClienteRequest request = new NovoClienteRequest("Brasil");
		request.setEstado("Bahia");
		
		System.out.println(validador.valida(request));
	}	
}
