package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.steps.entities.Job;
import com.demo.steps.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
      

    @Autowired
    private JobRepository repository;

    public List<Job> findAllJobs(){

        return repository.findAll();
    }

    public Job saveJob(Job newJob){ 
		newJob.setActive(false);
		newJob.setCreatedAt( LocalDateTime.now() ); //Seteamos la fecha
		
		return repository.save(newJob); //insert
	}
}
