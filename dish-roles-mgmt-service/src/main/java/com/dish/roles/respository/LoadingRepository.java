package com.dish.roles.respository;

import com.dish.roles.entity.LoadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadingRepository
        extends JpaRepository<LoadingEntity,Long> {
}
