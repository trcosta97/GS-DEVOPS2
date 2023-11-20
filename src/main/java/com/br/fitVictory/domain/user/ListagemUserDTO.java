package com.br.fitVictory.domain.user;

import com.br.fitVictory.domain.endereco.EnderecoDTO;

public record ListagemUserDTO(Long id, String nome, String email,Integer pontos) {

    public ListagemUserDTO(User user){
        this(user.getId(),user.getNome(), user.getEmail(), user.getPontuacao());
    }
}
