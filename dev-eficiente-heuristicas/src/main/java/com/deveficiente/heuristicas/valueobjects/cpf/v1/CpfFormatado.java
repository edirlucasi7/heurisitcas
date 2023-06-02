package com.deveficiente.heuristicas.valueobjects.cpf.v1;

public class CpfFormatado {

    private String cpf;

    private CpfFormatado(String cpf) {
        this.cpf = cpf;
    }

    public static String formataCpfSemPontuacao(String cpf) {
        return new CpfFormatado(cpf).toString();
    }
}
