package com.demo.steps.controller;

import java.util.*;

import com.demo.steps.entities.Job;
import com.demo.steps.service.JobService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService service;

    private final Log LOGGER = LogFactory.getLog(JobController.class);

    @GetMapping("") //Al invocar el route tasks con get nos devolvera la lista 
	public ResponseEntity< List< Job> > fetchAllTask(){
	
		
       	return ResponseEntity.ok().body( service.findAllJobs()); 
	}

    
	@PostMapping("")//Al invocar el route tasks con post guardara la tarea en la lista 
	public ResponseEntity<Job> postTask( @RequestBody Job newJob){ //Lo que venga en el requestBody lo va transformar en un objeto java
		
		//para debugear, de forma tradicional
		System.out.println("nueva tarea " + newJob);
		//Usando Log
		LOGGER.error("--nuevo job-- " + newJob);//mostrar un error
		LOGGER.warn("--nuevo job-- " + newJob);//advertencias
		LOGGER.info("--nuevo job-- " + newJob);//informacion
		LOGGER.debug("--nuevo job-- " + newJob);// debug

		//Devolvemos un created con el nuevo objeto creado en el body
        return ResponseEntity.status(HttpStatus.CREATED).body( service.saveJob(newJob));
        
	}
    
}
