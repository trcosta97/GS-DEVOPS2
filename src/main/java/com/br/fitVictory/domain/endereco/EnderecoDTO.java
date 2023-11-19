package com.br.fitVictory.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        @Size(max = 8, min = 8, message = "CEP deve conter 8 digitos")
        String cep
) {
}
