package com.br.fitVictory.repository;

import com.br.fitVictory.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
