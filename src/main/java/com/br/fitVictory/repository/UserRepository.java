package com.br.fitVictory.repository;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
