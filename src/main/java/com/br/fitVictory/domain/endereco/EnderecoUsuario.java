package com.br.fitVictory.domain.endereco;

import com.br.fitVictory.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco_usuario")
public class EnderecoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String rua;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

}
