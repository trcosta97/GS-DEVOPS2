package com.br.fitVictory.domain.user;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        String nome,
        String senha,
        @Email
        String email
) {
}
