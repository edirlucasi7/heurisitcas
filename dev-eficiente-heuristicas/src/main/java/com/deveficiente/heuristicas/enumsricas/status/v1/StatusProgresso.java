package com.deveficiente.heuristicas.enumsricas.status.v1;

public enum StatusProgresso {

	NAO_INICIADO {
		@Override
		boolean aceita(Inscricao inscricao) {
			return !inscricao.jaRespondeu();
		}
	},
	INICIADO {
		@Override
		boolean aceita(Inscricao inscricao) {
			return inscricao.jaRespondeu();
		}
	},
	FINALIZADO {
		@Override
		boolean aceita(Inscricao inscricao) {
			return inscricao.jaFinalizou();
		}
	};

	static StatusProgresso descobre(Inscricao inscricao) {
		StatusProgresso statusFinal = null;

		for (StatusProgresso status : StatusProgresso.values()) {
			if (status.aceita(inscricao)) {
				statusFinal = status;
			}
		}
		return statusFinal;
	}

	abstract boolean aceita(Inscricao inscricao);
}
