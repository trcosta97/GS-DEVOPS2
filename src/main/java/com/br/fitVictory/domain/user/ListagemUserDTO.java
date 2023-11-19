package com.br.fitVictory.domain.user;

import com.br.fitVictory.domain.endereco.EnderecoDTO;

public record ListagemUserDTO(String nome, String email) {

    public ListagemUserDTO(User user){
        this(user.getNome(), user.getEmail());
    }
}
