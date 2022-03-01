package com.demo.steps.entities;
//Esta clase sera nuestro modelo de la entidad
public class Task {

    private Long id;
    private String title;
    private String description;

    //Getter & setter

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
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

    
}
