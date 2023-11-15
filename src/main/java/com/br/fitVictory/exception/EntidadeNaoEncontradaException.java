package com.br.fitVictory.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(){
        super();
    }

    public EntidadeNaoEncontradaException(String message){
        super(message);
    }

}
