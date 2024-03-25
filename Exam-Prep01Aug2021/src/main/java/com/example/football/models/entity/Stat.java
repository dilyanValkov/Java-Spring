package com.example.football.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stats")
public class Stat extends BaseEntity{

    private float shooting;

    private float passing;

    private float endurance;

    @Override
    public String toString() {
        return String.format("Stat %.2f - %.2f - %.2f ",shooting,passing,endurance);
    }
}
