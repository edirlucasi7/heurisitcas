package com.deveficiente.heuristicas.coesaobasica.validarequest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		ArrayList<String> erros = new ArrayList<>();

		Optional<String> possivelEstado = request.getEstado();

		if (possivelEstado.isPresent()) {
			Pais pais = bancoDeDadosPaisesEEstados.buscaPaisPeloNome(request.getNomePais());
			Estado estado = bancoDeDadosPaisesEEstados.buscaEstadoPeloNome(possivelEstado.get());

			boolean estadoPertenceAoPais = estado.verificaSePertenceAoPais(pais);

			if (!estadoPertenceAoPais) {
				erros.add("O estado selecionado nao pertence ao Pais!");
			}
		}
		return erros;
	}
	
	public static void main(String[] args) {
		ValidaSeEstadoPertenceAPais validador = new ValidaSeEstadoPertenceAPais(
				new BancoDeDadosPaisesEEstados());
		
		/*
		 * Aqui fique a vontade para brincar com os valores para testar seu validador
		 */
		NovoClienteRequest request = new NovoClienteRequest("estados unidos");
		request.setEstado("Bahia");
		
		System.out.println(validador.valida(request));
	}	
}
