package com.project.demo.db;

import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    /**
     * Конструктор по умолчанию
     **/
    public Task(){

    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "task_value")
    private String value;

    public long getId() {
        return Id;
    }

    public void setId(long id){
        Id = id;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}
