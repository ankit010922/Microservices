package com.dish.roles.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LoadingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loadingId;
    @Column
    private String name;
    @Column
    private String work;
}
