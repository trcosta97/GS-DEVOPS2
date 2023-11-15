package com.br.fitVictory.domain.atividade;

import com.br.fitVictory.domain.endereco.EnderecoAtividade;
import com.br.fitVictory.domain.endereco.EnderecoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "atividade")
@Table(name = "atividade")
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "atividade_sequence", sequenceName = "atividade_sequence", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividade_sequence")
    @Column(name = "atividade_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_atividade")
    private TipoAtividade tipo;

    @Column(name = "descricao_atividade")
    private String descricao;

    @Column(name = "pontos")
    private Integer pontos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atividade")
    private StatusAtividade status;

    @JoinColumn(name = "local_atividade")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "atividade")
    private EnderecoAtividade endereco;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cadastro;

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

}
