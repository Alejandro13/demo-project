package com.demo.steps.controller;

import java.util.*;
import com.demo.steps.entities.Task;
import com.demo.steps.service.Tasks4Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controlador se quedó con la lógica para solo estar ruteando
 */

@RestController
@RequestMapping("steps/04/tasks")
public class Tasks4Controller {

    //Ahora invocamos al servicio, elemento que aplica lógica
    private final Tasks4Service service; //No se inicializa porque estamos usando spring, el se encarga de crear el componente

	//Creamos un Log para depurar nuestro codigo
	private final Log LOGGER = LogFactory.getLog(Tasks4Controller.class);

    //Constructor para inicializar el servicio
    public Tasks4Controller( @Autowired Tasks4Service service){// Autowired le dice a Spring que se encargue de crear el objeto service y pasarlo al controller
        this.service = service;
    }
    
	@GetMapping("") //Al invocar el route tasks con get nos devolvera la lista 
	public ResponseEntity<List< Task> > fetchAllTask(){
		
       	return ResponseEntity.ok().body( service.getAllTask()); 
	}

	@PostMapping("")//Al invocar el route tasks con post guardara la tarea en la lista 
	public ResponseEntity<Task> postTask( @RequestBody Task newTask){ //Lo que venga en el requestBody lo va transformar en un objeto java
		
		//para debugear, de forma tradicional
		System.out.println("nueva tarea " + newTask);
		//Usando Log
		LOGGER.error("--nueva tarea-- " + newTask);//mostrar un error
		LOGGER.warn("--nueva tarea-- " + newTask);//advertencias
		LOGGER.info("--nueva tarea-- " + newTask);//informacion
		LOGGER.debug("--nueva tarea-- " + newTask);

		//Devolvemos un created con el nuevo objeto creado en el body
        return ResponseEntity.status(HttpStatus.CREATED).body( service.saveTask(newTask));
        
	}

	@GetMapping("/{taskId}") ///{variable}
	public ResponseEntity<Task> fetchTaskById( @PathVariable("taskId") Long taskId ){
		return service.getTaskById(taskId)
				.map( task -> ResponseEntity.ok().body(task)) //si ese elemento es != de null, se regresa el task y un cogido ok
				.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND ).build() ); //Si es null
	}


	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteTask( @PathVariable("taskId") Long taskId){
        boolean answer = service.deleteTask(taskId); //Para saber si lo borro o no
		LOGGER.debug("RESULTADO DESDE CONTROLLER: " + answer);

        if(answer){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		
	}

    //Accion Update
	@PutMapping("/{taskId}") 
	public ResponseEntity<Task> putTask( @PathVariable("taskId") Long taskId, @RequestBody Task updateTask){
		//return " update";
		return service.updateTask(taskId, updateTask)
						.map( task -> {							
							return ResponseEntity.ok().body(task); //devuelve la tarea actualizada
						})
						.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
    
}
