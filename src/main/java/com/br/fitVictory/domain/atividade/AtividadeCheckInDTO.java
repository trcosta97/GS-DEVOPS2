package com.br.fitVictory.domain.atividade;

import com.br.fitVictory.domain.endereco.EnderecoAtividade;
import com.br.fitVictory.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;

public record AtividadeCheckInDTO(
        @NotBlank(message = "É necessário informar o tipo de atividade")
        TipoAtividade tipo,
        String descricao,
        Integer pontos,
        EnderecoDTO enderecoAtividade
    )
{
}
