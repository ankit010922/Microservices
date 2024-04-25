package com.dish.auth.respository;

import com.dish.auth.entity.AuthEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long>{

	Optional<AuthEntity> findByEmail(String email);

}
