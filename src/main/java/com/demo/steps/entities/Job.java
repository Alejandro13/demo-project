package com.demo.steps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;



//Esta clase sera nuestro modelo de la entidad
@Entity // de JPA
@Table(name = "jobs") // en runtume se genera el esquema de la BD
//USANDO LOMBOK
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @Column( name = "id" ) //si el nombre de la columna es igual al nombre de mi atributo, podemos omitir este paso
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Long jobId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name =  "owner_id")
    private String ownerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
}
