package com.demo.steps.controller;

import java.util.List;

import com.demo.steps.entities.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //da la capacidad de exponer el api por medio de http
@RequestMapping("/tasks") //Para modificar la ruta de acceso -> /tasks/all
public class Tasks1Controller {
    //Endpoint
	//Despues del 8080 va el /hi
	
	@GetMapping("/all")
	public ResponseEntity<List< Task> > fetchAllTask(){
		//return "[ returning all tasks ";
       //return ResponseEntity.ok().body("ALL TASKS");
        return null;
	}

	@PostMapping("/save")
	public ResponseEntity<Task> postTask( Task newTask){
		//return "saving..";
        //return ResponseEntity.status(HttpStatus.CREATED).body("task created");
        return null;
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteTask( Long taskId){
		//return "delete...";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/update")
	public ResponseEntity<String> putTask( Long taskId, Task updateTask){
		//return " update";
        return ResponseEntity.ok().body("update");
	}
    
}
