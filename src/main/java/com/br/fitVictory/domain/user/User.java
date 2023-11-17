package com.br.fitVictory.domain.user;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.endereco.EnderecoUsuario;
import com.br.fitVictory.domain.temperatura.Temperatura;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id")
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
    private String roles;

    @JoinColumn(name = "endereco_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private EnderecoUsuario enderecoUsuario;

    @Column(name = "temperatura")
    @OneToMany(mappedBy = "user")
    private List<Temperatura> temperatura;

    @Column(name = "status")
    private boolean status;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cadastro;


    @Column(name = "pontuacao")
    private Integer pontuacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Atividade> atividades;

    public User(UserCadastroDTO data) {
        this.nome = data.nome();
        this.senha = data.senha();
        this.email = data.email();
        this.enderecoUsuario = new EnderecoUsuario(data.enderecoUsuario());

    }

    public User(UserUpdateDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.senha = data.senha();
    }

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.pontuacao = 0;
        this.status = true;
        this.roles = "USER";
    }

    public void addAtividade(Atividade atividade){
        this.atividades.add(atividade);
        this.pontuacao = pontuacao + atividade.getPontos();

    }
}
