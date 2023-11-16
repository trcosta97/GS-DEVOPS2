package com.br.fitVictory.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        String numero,
        @NotBlank(message = "Nome da rua não pode ser nulo")
        String rua,
        @NotBlank(message = "Nome da cidade não pode ser nulo")
        String cidade,
        @NotBlank(message = "Nome do estado não pode ser nulo")
        String estado,
        @NotBlank(message = "Nome do país não pode ser nulo")
        String pais,
        @NotBlank(message = "CEP não pode ser nulo")
        String cep
) {
}
