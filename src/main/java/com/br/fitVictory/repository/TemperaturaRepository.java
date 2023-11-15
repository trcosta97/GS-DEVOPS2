package com.br.fitVictory.repository;

import com.br.fitVictory.domain.temperatura.Temperatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperaturaRepository extends JpaRepository<Temperatura, Long> {
}
