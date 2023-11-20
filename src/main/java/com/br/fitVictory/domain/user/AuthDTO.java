package com.br.fitVictory.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "Preencha o campo de email")
        String email,
        @NotBlank(message = "Preencha o campo de senha")
        String senha) {
}
