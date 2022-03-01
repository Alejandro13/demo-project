package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController //da la capacidad de exponer el api por medio de http
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
				
	}
	//Endpoint
	//Despues del 8080 va el /hi
	@GetMapping("/hi")
	public String sayHello(){
		return "Hola Alex";
	}

	@GetMapping("/all")
	public String fetchAllTask(){
		return "[ returning all tasks ";
	}

	@PostMapping("/save")
	public String postTask(){
		return "saving..";
	}

	@DeleteMapping("/delete")
	public String deleteTask(){
		return "delete...";
	}

	@PutMapping("/update")
	public String putTask(){
		return " update";
	}
	

}
