package com.br.fitVictory.domain.atividade;

public record ListagemAtividadesDTO(TipoAtividade tipo, String descricao, Integer pontos) {

    public ListagemAtividadesDTO(Atividade atividade){
        this(atividade.getTipo(), atividade.getDescricao(), atividade.getPontos());
    }
}
