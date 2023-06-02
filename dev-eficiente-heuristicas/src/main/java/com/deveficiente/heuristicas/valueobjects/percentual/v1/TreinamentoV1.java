package com.deveficiente.heuristicas.valueobjects.percentual.v1;

import java.util.HashMap;
import java.util.Map;

public class TreinamentoV1 {

	private int numeroDeAulas;
	private Map<String, Integer> aulasFeitasPorAluno = new HashMap<>();

	public TreinamentoV1(int numeroDeAulas) {
		this.numeroDeAulas = numeroDeAulas;
	}

	public Percentual percentualFeito(String emailAluno) {
		return new Percentual(aulasFeitasPorAluno.getOrDefault(emailAluno, 0), numeroDeAulas);
	}

	public void setAulasFeitasPorAluno(Map<String, Integer> aulasFeitasPorAluno) {
		this.aulasFeitasPorAluno = aulasFeitasPorAluno;
	}
}
