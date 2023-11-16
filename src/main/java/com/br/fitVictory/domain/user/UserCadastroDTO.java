package com.br.fitVictory.domain.user;


import com.br.fitVictory.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;

public record UserCadastroDTO(
        @NotBlank(message = "Nome não pode ser nulo")
        String nome,
        @NotBlank(message = "Email não pode ser nulo")
        String email,
        @NotBlank(message = "senha não pode ser nulo")
        String senha,
        @NotBlank(message = "CPF não pode ser nulo")
        String cpf,
        EnderecoDTO enderecoUsuario


) {
}
