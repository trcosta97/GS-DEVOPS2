package com.br.fitVictory.domain.endereco;

import com.br.fitVictory.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    public EnderecoUsuario(EnderecoDTO data) {
        this.numero = data.numero();
        this.rua = data.rua();
        this.cidade = data.cidade();
        this.estado = data.estado();
        this.pais = data.pais();
        this.cep = data.cep();
    }
}
