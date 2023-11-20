package com.br.fitVictory.repository;

import com.br.fitVictory.domain.atividade.Atividade;
import com.br.fitVictory.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM atividade a WHERE a.user.id = :userId")
    Page<Atividade> getAtividadesByUserId(Long userId, Pageable pageable);

    UserDetails findByEmail(String email);
}
