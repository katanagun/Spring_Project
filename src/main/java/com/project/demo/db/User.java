package com.project.demo.db;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    /**
     * Конструктор по умолчанию
     */
    public User(){

    }

    public long getId(){
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "user_name")
    private String name;

    private String type;
}
