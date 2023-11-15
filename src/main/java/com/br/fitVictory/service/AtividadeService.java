package com.br.fitVictory.service;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.user.User;
import com.br.fitVictory.exception.EntidadeNaoEncontradaException;
import com.br.fitVictory.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    public Atividade save(Atividade atividade){
        return repository.save(atividade);
    }

    public Atividade get(Long id){
        Optional<Atividade> optionalAtividade = repository.findById(id);
        if(optionalAtividade.isPresent()){
            return optionalAtividade.get();
        }
        throw new EntidadeNaoEncontradaException("Atividade não encontrado.");
    }

    public List<Atividade> getAll(){
        return repository.findAll();
    }

    public Atividade update(Long id, Atividade data){
        Optional<Atividade> optionalAtividade = repository.findById(id);
        if (optionalAtividade.isPresent()){
            Atividade updated = optionalAtividade.get();
            if(data.getDescricao() != null){
                updated.setDescricao(data.getDescricao());
            }
            if(data.getPontos() != null){
                updated.setPontos(data.getPontos());
            }
            return repository.save(updated);
        }
        throw new EntidadeNaoEncontradaException("Atividade não encontrado.");
    }

    public void delete(Long id){
        Optional<Atividade> optionalAtividade = repository.findById(id);
        if(optionalAtividade.isPresent()){
            repository.deleteById(id);
        }
        throw new EntidadeNaoEncontradaException("Atividade não encontrado.");
    }

}

