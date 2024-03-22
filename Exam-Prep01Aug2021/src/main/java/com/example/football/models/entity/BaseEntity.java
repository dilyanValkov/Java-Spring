package com.example.football.models.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
