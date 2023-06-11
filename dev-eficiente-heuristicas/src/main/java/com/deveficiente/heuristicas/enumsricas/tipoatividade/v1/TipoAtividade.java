package com.deveficiente.heuristicas.enumsricas.tipoatividade.v1;

public enum TipoAtividade {

	CONVENCIONAL(true),
	IMITACAO(false),
	EXEMPLO_TRABALHADO(false);

	public boolean obrigatorio;

	TipoAtividade(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}
}
