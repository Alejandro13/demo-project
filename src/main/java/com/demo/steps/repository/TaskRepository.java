package com.demo.steps.repository;

import com.demo.steps.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;

//una interfaz es un contrado con obligaciones
public interface TaskRepository extends JpaRepository<Task,Long> { //< TASK, clase que representa nuestra identidad de la BD , Long llave primaria > 
    //Al heredar de jpa no es necesario declarar los metodos aquí

    //Query methods concatenan palabras para realizar operaciones sql
    //busquedas -> find
    //Borrado -> delete
    //by -> where
    //find -> select
    
    //delete from table where taskId = taskId 
    Long deleteByTaskId( Long taskId); //-> va a intentar borrar una tarea por medio del atributo taskId, el Long es el número de registros que borró de la DB


    
}
