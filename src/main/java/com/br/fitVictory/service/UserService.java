package com.br.fitVictory.service;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.temperatura.Temperatura;
import com.br.fitVictory.domain.user.User;
import com.br.fitVictory.exception.EntidadeNaoEncontradaException;
import com.br.fitVictory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user){
        List<Temperatura> temperaturas = new ArrayList<>();
        user.setTemperatura(temperaturas);
        return repository.save(user);
    }

    public User get(Long id){
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
    }

    public Page<User> getAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public User update(Long id, User data){
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()){
            User updated = optionalUser.get();
            if(data.getNome() != null){
                updated.setNome(data.getNome());
            }
            if(data.getEmail() != null){
                updated.setEmail(data.getEmail());
            }
            if(data.getSenha() != null){
                updated.setSenha(data.getSenha());
            }
            return repository.save(updated);
        }else if (optionalUser.isEmpty()){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }
        return null;
    }

    public void delete(Long id){
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            repository.deleteById(id);
        }else if (optionalUser.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }
    }

    public User addAtividade(Long userId, Atividade atividade){
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            atividade.setUser(user);
            user.addAtividade(atividade);
            repository.save(user);
        }else if (optionalUser.isEmpty()){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }
       return null;
    }

    public List<Atividade> getAtividades(Long userId){
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()){
            User foundUser = optionalUser.get();
            List<Atividade> atividades = foundUser.getAtividades();
            return atividades;
        }else if (optionalUser.isEmpty()){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }
        return null;
    }

}
