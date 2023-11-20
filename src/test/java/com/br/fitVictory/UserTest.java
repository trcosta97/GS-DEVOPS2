package com.br.fitVictory;

import com.br.fitVictory.domain.endereco.EnderecoDTO;
import com.br.fitVictory.domain.user.Roles;
import com.br.fitVictory.domain.user.User;
import com.br.fitVictory.exception.EntidadeNaoEncontradaException;
import com.br.fitVictory.repository.UserRepository;
import com.br.fitVictory.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;


    @Test
    public void testFindByIdThrowEntidadeNaoEncontradaException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.get(2L));
    }

    @Test
    public void testFindByIdReturnSuccess() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setNome("Thiago");
        user.setEmail("trcosta97@fiap.com.br");
        user.setSenha("123");
        user.setCpf("42569887852");

        Long idUser = 1L;
        when(repository.findById(idUser)).thenReturn(Optional.of(user));

        User userFound = service.get(idUser);

        assertNotNull(userFound);
        assertEquals("Thiago", userFound.getNome());
    }
}


