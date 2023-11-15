package com.br.fitVictory.domain.temperatura;

import com.br.fitVictory.domain.user.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity(name = "temperatura")
@Table
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "temperatura_pontuacao", sequenceName = "temperatura_pontuacao", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Temperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temperatura_sequence")
    @Column(name = "temperatura_id")
    private Long id;

    @Column(name = "temperatura_value")
    private Double temperatura;

    @Column(name = "data_temperatura")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataTemperatura;

    @Column(name = "local_temperatura")
    private String local;

    @JoinColumn(name = "cliente_id")
    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist(){
        this.dataTemperatura = LocalDateTime.now();
    }


}
