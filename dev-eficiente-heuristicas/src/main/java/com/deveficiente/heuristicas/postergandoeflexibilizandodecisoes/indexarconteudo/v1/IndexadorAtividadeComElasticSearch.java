package com.deveficiente.heuristicas.postergandoeflexibilizandodecisoes.indexarconteudo.v1;

public class IndexadorAtividadeComElasticSearch implements IndexadorDeBuscaDeAtividade {

    @Override
    public void indexa(Atividade atividade) {
        System.out.println("Indexando atividade com elastic search"+atividade);
    }
}
