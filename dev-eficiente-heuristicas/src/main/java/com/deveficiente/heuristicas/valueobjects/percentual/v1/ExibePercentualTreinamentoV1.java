package com.deveficiente.heuristicas.valueobjects.percentual.v1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ExibePercentualTreinamentoV1 {

	public static void main(String[] args) {
		TreinamentoV1 treinamentoV1 = new TreinamentoV1(10);

		treinamentoV1.setAulasFeitasPorAluno(Map.of("email@email.com", 20));
		
		/*
		 * Como você sabe o numero de casas decimais aqui? Qual 
		 * foi o arrendondamento?
		 * 
		 * Como você pode refatorar para que o retorno possa ser utilizado
		 * de tal maneira que a pessoa não precise olhar a implementação? 
		 */
		Percentual percentualFeito = treinamentoV1.percentualFeito("email@email.com");
		
		System.out.println(percentualFeito.calcula(3, RoundingMode.DOWN));
	}
}
