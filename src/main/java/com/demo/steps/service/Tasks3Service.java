package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.*;

import com.demo.steps.entities.Task;
import com.demo.steps.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//ESTE SERVICIO UTILIZA LOS METODOS DE JPA

@Service //Elemento que controla spring que va inyectar, spring lo crea con todas la dependencia
public class Tasks3Service {

    private final TaskRepository repository;

    //Constructor
    public Tasks3Service( @Autowired TaskRepository repository){
        this.repository = repository;
    }

	public List<Task> getAllTask(){ //no es un componente web, por eso quitamos ResponseEntity		
       	return repository.findAll(); //método de JPA , select * from table;       
	}

	
	public Task saveTask(Task newTask){ 
		newTask.setActive(false);
		newTask.setCreatedAt( LocalDateTime.now() ); //Seteamos la fecha
		
		return repository.save(newTask); //insert
	}

    //Si existe el objeto, optional regresa el objeto, sino existe
	public Optional<Task> getTaskById( Long taskId ){
		return	repository.findById(taskId); //select id from table;
						
	}
	
	public boolean deleteTask( Long taskId){
		try { //provisional
			repository.deleteById(taskId);
			return getTaskById(taskId).isEmpty(); //pregunta si borró la tarea	
		} catch (Exception e ) {
			System.out.println( e );
			return false;
		}
	}

    //Si encuentra el objeto, lo actualiza, sino tiene que notificar que no se localizo el objeto
	public Optional<Task> updateTask(  Long taskId, Task updateTask){
		//return " update";
		return getTaskById(taskId)
						.map( task -> {
							task.setTitle( updateTask.getTitle());
							task.setDescription( updateTask.getDescription() );
							task.setUpdateAt( LocalDateTime.now() );
							return repository.save(task); //sino existe la tarea, la agrega, si existe hace un update
						});						
	}
    
}
