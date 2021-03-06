package com.demo.steps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Esta clase sera nuestro modelo de la entidad
@Entity // de JPA
@Table(name = "tasks") // en runtume se genera el esquema de la BD
public class Task {

    @Id
    @Column( name = "id" ) //si el nombre de la columna es igual al nombre de mi atributo, podemos omitir este paso
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Long taskId;

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

    

    
    //Método toString para saber el estado del objeto   
    
    @Override
    public String toString() {
        return "Task [active=" + active + ", description=" + description + ", ownerId=" + ownerId + ", taskId=" + taskId
                + ", title=" + title + "]";
    }

    //Getter & setter

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }    
    
   
    public Long getTaskId() {
        return taskId;
    }
    

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    

    
}
