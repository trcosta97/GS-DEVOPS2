package com.br.fitVictory.repository;

import com.br.fitVictory.domain.atividade.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
