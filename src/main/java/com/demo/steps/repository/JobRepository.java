package com.demo.steps.repository;

import com.demo.steps.entities.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{ //<Clase que representa mi tabla, tipo de dato para la llave primaria>
    //Aqui podemos sobreescribir los métodos de jpa para consulta sql
}
