package com.deveficiente.heuristicas.enumsricas.status.v1;

import java.util.ArrayList;
import java.util.List;

import static com.deveficiente.heuristicas.enumsricas.status.v1.StatusProgresso.*;

public class Inscricao {

	private Treinamento treinamento;
	private Aluno aluno;
	private List<Resposta> respostas = new ArrayList<>();
	private StatusProgresso statusProgresso;

	public Inscricao(Aluno aluno, Treinamento treinamento, StatusProgresso statusProgresso) {
		super();
		this.aluno = aluno;
		this.treinamento = treinamento;
		this.statusProgresso = statusProgresso;
	}

	public void addResposta(Atividade atividade) {
		this.respostas.add(new Resposta(this, atividade));
	}

	public StatusProgresso calculaProgresso() {
		return StatusProgresso.descobre(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result
				+ ((treinamento == null) ? 0 : treinamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inscricao other = (Inscricao) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (treinamento == null) {
			if (other.treinamento != null)
				return false;
		} else if (!treinamento.equals(other.treinamento))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Atividade atividade1 = new Atividade("atividade 1");
		Atividade atividade2 = new Atividade("atividade 2");
		Atividade atividade3 = new Atividade("atividade 3");

		Treinamento treinamento = new Treinamento("titulo",
				List.of(atividade1, atividade2, atividade3));
		
		Aluno aluno = new Aluno("pessoa@deveficiente.com");

		Inscricao inscricao = new Inscricao(aluno, treinamento, NAO_INICIADO);

		inscricao.addResposta(atividade1);
		inscricao.addResposta(atividade2);
		inscricao.addResposta(atividade3);

		System.out.println(inscricao.calculaProgresso());
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public boolean jaRespondeu() {
		return !this.respostas.isEmpty();
	}

	public boolean jaFinalizou() {
		return this.treinamento.estaTodoRespondido(respostas);
	}
}
