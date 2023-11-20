package com.br.fitVictory.domain.user;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.endereco.EnderecoUsuario;
import com.br.fitVictory.domain.temperatura.Temperatura;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "cliente_user")
@Table
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails {

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
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @JoinColumn(name = "endereco_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private EnderecoUsuario enderecoUsuario;

    @Column(name = "temperatura")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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

    public User(UserCadastroDTO data, String encryptedPassword) {
        this.nome = data.nome();
        this.roles = data.role();
        this.email = data.email();
        this.senha = encryptedPassword;
        this.enderecoUsuario = new EnderecoUsuario(data.enderecoUsuario());
    }


    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.pontuacao = 0;
        this.status = true;
        if(this.roles != Roles.ADMIN){
            this.roles = Roles.USER;
        }

    }

    public void addAtividade(Atividade atividade){
        this.atividades.add(atividade);
        this.pontuacao = pontuacao + atividade.getPontos();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles == Roles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
