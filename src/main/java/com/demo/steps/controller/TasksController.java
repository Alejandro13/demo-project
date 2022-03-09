package com.demo.steps.controller;

import java.util.*;
import com.demo.steps.entities.Task;
import com.demo.steps.service.ITaskService;
import com.demo.steps.service.TasksServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controlador se quedó con la lógica para solo estar ruteando
 */

@RestController
@RequestMapping("steps/06/tasks")
public class TasksController {

    //Ahora invocamos al servicio, elemento que aplica lógica
    private final ITaskService service; //No se inicializa porque estamos usando spring, el se encarga de crear el componente, USAMOS LA INTERFAZ DE SERVICIO

	//Creamos un Log para depurar nuestro codigo
	private final Log LOGGER = LogFactory.getLog(TasksController.class);

	//Es final para que una vez que sea asignada su valor ya nadie la cambie después
	private final String yamlValue;
	private final String projectName;

    //Constructor para inicializar el servicio
    public TasksController( @Qualifier("taskService") ITaskService service,// indicamos que TaskService(bean) vamos a utilizar

		@Value("${myYamlValue}") String yamlValue, //variable de ambiente
		@Value("${PROJECT_NAME}")String projectName){//Obtenemos variable de ambiente desde archivo yaml

        this.service = service;
		this.yamlValue = yamlValue;
		this.projectName = projectName;
    }
    
	@GetMapping("") //Al invocar el route tasks con get nos devolvera la lista 
	public ResponseEntity<List< Task> > fetchAllTask(){
		LOGGER.debug("### MY YAML VALUE IS : ### " + yamlValue);
		LOGGER.debug("### MY ENVIROMENT VALUE FROM LAUNCH JSON IS : ### " + projectName);
		
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

	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<Task>> fetchTaskByOwner( @PathVariable("ownerId") String ownerId){
		return ResponseEntity.ok().body(service.findByOwnerId(ownerId));				
	}	

	@GetMapping("/owner/{ownerId}/active")
	public ResponseEntity<List<Task>> fetchTaskByOwnerAndStatus(
		@PathVariable("ownerId") String ownerId ,
		@RequestParam(name = "status") boolean active){
		return ResponseEntity.ok().body(service.findByOwnerIdAndStatus(ownerId, active));
	}

	//En caso de no mandar status podemos usar optional y dar un valor por default
	@GetMapping("/owner/{ownerId}/optional")
	public ResponseEntity<List<Task>> fetchTaskByOwnerAndOptionalStatus( 
		@PathVariable("ownerId") String ownerId ,
		@RequestParam(name = "status", defaultValue = "true" ) boolean active){
		return ResponseEntity.ok().body(service.findByOwnerIdAndStatus(ownerId, active));
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
