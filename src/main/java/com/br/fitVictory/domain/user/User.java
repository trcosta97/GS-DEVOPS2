package com.br.fitVictory.domain.user;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.endereco.EnderecoUsuario;
import com.br.fitVictory.domain.pontuacao.Pontuacao;
import com.br.fitVictory.domain.temperatura.Temperatura;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "cliente_user")
@Table
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "cliente_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "roles")
    private List<String> roles;

    @JoinColumn(name = "endereco_cliente")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private EnderecoUsuario enderecoUsuario;

    @Column(name = "temperatura")
    @OneToMany(mappedBy = "user")
    private List<Temperatura> temperatura;

    @Column(name = "status")
    private boolean status;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cadastro;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "pontuacao")
    private Pontuacao pontuacao;

    @OneToMany()
    @JoinColumn(name = "atividades")
    private List<Atividade> atividades;

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

    public void addAtividade(Atividade atividade){
        this.atividades.add(atividade);
        this.pontuacao.addPontos(atividade);

    }
}
