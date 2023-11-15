package com.br.fitVictory.domain.pontuacao;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "pontuacao")
@Table
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "pontuacao_sequence", sequenceName = "pontuacao_sequence", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontuacao_sequence")
    @Column(name = "pontuacao_id")
    private Long id;
    @Column(name = "score_value")
    private Integer pontos;
    @JoinColumn(name = "cliente_id")
    @OneToOne
    private User user;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cadastro;


    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

    public void addPontos(Atividade atividade){
        this.pontos = pontos + atividade.getPontos();

    }
}
