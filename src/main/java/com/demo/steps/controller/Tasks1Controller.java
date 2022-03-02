package com.demo.steps.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.steps.entities.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //da la capacidad de exponer el api por medio de http
@RequestMapping("/tasks") //Para modificar la ruta de acceso -> /tasks/accion
public class Tasks1Controller {

	private final List<Task> taskList = new ArrayList<>(); 

    //Endpoint
	//Despues del 8080 va el /	
	@GetMapping("") //Al invocar el route tasks con get nos devolvera la lista 
	public ResponseEntity<List< Task> > fetchAllTask(){
		//return "[ returning all tasks ";
       	return ResponseEntity.ok().body(taskList); //devolvemos una lista de tareas
        
	}

	@PostMapping("")//Al invocar el route tasks con post guardara la tarea en la lista 
	public ResponseEntity<Task> postTask( @RequestBody Task newTask){ //Lo que venga en el requestBody lo va transformar en un objeto java
		newTask.setActive(false);
		newTask.setCreatedAt( LocalDateTime.now() ); //Seteamos la fecha
		
		long id =  taskList.size() + 1 ;
		newTask.setId(id);

		taskList.add(newTask); //agregamos la tarea a la lista
		
		//return "saving..";
		//Devolvemos un created con el nuevo objeto creado en el body
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
        
	}

	@GetMapping("/{taskId}") ///{variable}
	public ResponseEntity<Task> fetchTaskById( @PathVariable("taskId") Long taskId ){
		return	taskList
				.stream()
				.filter( current -> taskId == current.getId() ) //es la condicion, te devuelve el array que encuentre
				.findFirst() // te trae el primer elemento que encuentre
				.map( task -> ResponseEntity.ok().body(task)) //si ese elemento es != de null, se regresa el task y un cogido ok
				.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND ).build() ); //Si es null
	}


	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteTask( @PathVariable("taskId") Long taskId){
		return taskList
						.stream() //
						.filter( current -> taskId == current.getId() )
						.findFirst()
						.map( task -> {
							taskList.remove( task);//borra el elemento de la lista
							return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
						}) //para varias opciones
						.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		//return "delete...";
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<Task> putTask( @PathVariable("taskId") Long taskId, @RequestBody Task updateTask){
		//return " update";
		return taskList
						.stream() //
						.filter( current -> taskId == current.getId() )
						.findFirst()
						.map( task -> {
							task.setTitle( updateTask.getTitle());
							task.setDescription( updateTask.getDescription() );
							task.setUpdateAt( LocalDateTime.now() );
							return ResponseEntity.ok().body(task); //devuelve la tarea actualizada
						})
						.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
    
}
