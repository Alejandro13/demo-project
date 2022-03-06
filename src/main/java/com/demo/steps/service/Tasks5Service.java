package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.*;

import com.demo.steps.entities.Task;
import com.demo.steps.repository.TaskRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//ESTE SERVICIO UTILIZA LOS METODOS DE JPA

@Service //Elemento que controla spring que va inyectar, spring lo crea con todas la dependencia
public class Tasks5Service {

    private final TaskRepository repository;

	private Log LOGGER = LogFactory.getLog(Tasks5Service.class);

    //Constructor
    public Tasks5Service( @Autowired TaskRepository repository){
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
	
	@Transactional //> sincroniza las operaciones con la base de datos
	public boolean deleteTask( Long taskId){
		try { 
			//repository.deleteById(taskId);
			//return getTaskById(taskId).isEmpty(); //pregunta si borró la tarea	
			Long rows = repository.deleteByTaskId(taskId);
			LOGGER.debug("FILAS BORRADAS: " + rows);

			return rows == 1 ? true : false;

		} catch (Exception e ) {
			//System.out.println( e );
			LOGGER.error("### ERROR AL BORRAR ELEMENTO: ", e);
			return false;
		}
	}

    //Si encuentra el objeto, lo actualiza, sino tiene que notificar que no se localizo el objeto
	public Optional<Task> updateTask(  Long taskId, Task updateTask ){
		//return " update";
		return getTaskById(taskId)
						.map( task -> {
							task.setTitle( updateTask.getTitle());
							task.setDescription( updateTask.getDescription() );
							task.setUpdateAt( LocalDateTime.now() );
							return repository.save(task); //sino existe la tarea, la agrega, si existe hace un update
						});						
	}


	public List<Task> findByOwnerId( String ownerId ){
		return repository.findByOwnerId(ownerId);
	}

	public List<Task> findByOwnerIdAndStatus(String ownerId, boolean status){
		return repository.findByOwnerIdAndActive(ownerId, status);
	}


    
}
