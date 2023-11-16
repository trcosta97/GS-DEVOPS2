package com.br.fitVictory.domain.user;

public record UserUpdateDTO(
        String nome,
        String senha,
        String email
) {
}
