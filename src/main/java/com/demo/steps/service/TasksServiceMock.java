package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.*;

import com.demo.steps.entities.Task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service //Elemento que controla spring que va inyectar, spring lo crea con todas la dependencia
@Qualifier("mockService") //Sirve para poner un nombre, es una forma de decir que es una clase que implementa una interfaz en particulas pero la vamos a diferenciar
public class TasksServiceMock implements ITaskService {

	//SIMULAMOS LAS OPERACIONES

    private final List<Task> taskList = new ArrayList<>(); 

	public List<Task> getAllTask(){ //no es un componente web, por eso quitamos ResponseEntity		
       	return taskList;        
	}

	
	public Task saveTask(Task newTask){ 
		newTask.setActive(false);
		newTask.setCreatedAt( LocalDateTime.now() ); //Seteamos la fecha
		
		long id =  taskList.size() + 1 ;
		newTask.setTaskId(id);

		taskList.add(newTask); //agregamos la tarea a la lista	
		
        return newTask;        
	}

    //Si existe el objeto, optional regresa el objeto, sino existe
	public Optional<Task> getTaskById( Long taskId ){
		return	taskList
				.stream()
				.filter( current -> taskId == current.getTaskId() ) //es la condicion, te devuelve el array que encuentre
				.findFirst(); // te trae el primer elemento que encuentre			
	}
	
	public boolean deleteTask( Long taskId){
		return taskList
						.stream() //
						.filter( current -> taskId == current.getTaskId() )
						.findFirst()
						.map( task -> {
							taskList.remove( task);//borra el elemento de la lista
							return true;
						}) //para varias opciones
						.orElse( false);
	}

    //Si encuentra el objeto, lo actualiza, sino tiene que notificar que no se localizo el objeto
	public Optional<Task> updateTask(  Long taskId, Task updateTask){
		//return " update";
		return taskList
						.stream() //
						.filter( current -> taskId == current.getTaskId() )
						.findFirst()
						.map( task -> {
							task.setTitle( updateTask.getTitle());
							task.setDescription( updateTask.getDescription() );
							task.setUpdateAt( LocalDateTime.now() );
							return task; //devuelve la tarea actualizada
						});						
	}

	//SE AGREGAN LOS MÉTODOS DE LA INTERFAZ QUE NOS HACIAN FALTA
	//LA ANOTACIÓN @OVERRIDE ES POR BUENA PRÁCTICA INDICANDO LA IMPLEMENTACION DE LA INTERFAZ
	@Override
	public List<Task> findByOwnerId(String ownerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Task> findByOwnerIdAndStatus(String ownerId, boolean status) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
