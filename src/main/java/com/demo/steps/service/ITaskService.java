package com.demo.steps.service;

import java.util.*;
import com.demo.steps.entities.Task;
import org.springframework.transaction.annotation.Transactional;

//ESTE SERVICIO UTILIZA LOS METODOS DE JPA

public interface ITaskService {

	//Aqui solo tenemos la defición de métodos que alguien tiene que cumplir   
   

	public List<Task> getAllTask();
	
	public Task saveTask(Task newTask);

    //Si existe el objeto, optional regresa el objeto, sino existe
	public Optional<Task> getTaskById( Long taskId );
	
	@Transactional //> sincroniza las operaciones con la base de datos
	public boolean deleteTask( Long taskId);

    //Si encuentra el objeto, lo actualiza, sino tiene que notificar que no se localizo el objeto
	public Optional<Task> updateTask(  Long taskId, Task updateTask );

	public List<Task> findByOwnerId( String ownerId );

	public List<Task> findByOwnerIdAndStatus(String ownerId, boolean status);

	//Default methods, caracteristica de java 8

	default String printAllTask( List<Task> taskList){

		
		return taskList
						.stream()
						.map( current -> current.toString()) //generar una lista de Strings
						.reduce( "" , ( concatenated , current) -> concatenated + current); //valor inicial , (lambda que tiene los valores, valor current) -> concatenamos las tareas Strings
	}
    
}
