package com.dish.roles.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dish.roles.entity.RoleEntity;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long>{

}
