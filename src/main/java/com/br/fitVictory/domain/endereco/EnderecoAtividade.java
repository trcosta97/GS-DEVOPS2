package com.br.fitVictory.domain.endereco;

import com.br.fitVictory.domain.atividade.Atividade;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco_atividade")
public class EnderecoAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String rua;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    @JoinColumn(name = "atividade_id")
    @OneToOne
    private Atividade atividade;

}
