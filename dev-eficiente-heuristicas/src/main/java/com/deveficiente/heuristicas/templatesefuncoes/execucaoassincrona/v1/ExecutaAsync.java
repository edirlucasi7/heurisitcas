package com.deveficiente.heuristicas.templatesefuncoes.execucaoassincrona.v1;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Component
public class ExecutaAsync {

    @Async
    public void semRetorno(Runnable funcao) {
        System.out.println("Executando de maneira assíncrona");
        funcao.run();
    }

    @Async
    public <T> CompletableFuture<T> comRetorno(Supplier<T> funcao) {
        System.out.println("Executando de maneira assíncrona");
        return CompletableFuture.completedFuture(funcao.get());
    }
}
