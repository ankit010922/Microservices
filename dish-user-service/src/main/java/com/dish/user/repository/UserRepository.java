package com.dish.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dish.user.entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

}
