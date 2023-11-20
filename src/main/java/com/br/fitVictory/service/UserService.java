package com.br.fitVictory.service;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.temperatura.Temperatura;
import com.br.fitVictory.domain.user.User;
import com.br.fitVictory.exception.EntidadeNaoEncontradaException;
import com.br.fitVictory.repository.AtividadeRepository;
import com.br.fitVictory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User save(User user){
        user.setSenha(passwordEncoder.encode(user.getPassword()));
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

    public Page<Atividade> getAtividades(Long userId, Pageable paginacao){
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()){

            Page<Atividade> atividades = repository.getAtividadesByUserId(userId, paginacao);
            return atividades;
        }else if (optionalUser.isEmpty()){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
        }
        return null;
    }




}
