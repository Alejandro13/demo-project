package com.demo.steps.repository;

import com.demo.steps.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;

//una interfaz es un contrado con obligaciones
public interface TaskRepository extends JpaRepository<Task,Long> { //< clase que representa nuestra identidad de la BD , llave primaria > 
    //Al heredar de jpa no es necesario declarar los metodos aquí
    
}
