package com.project.demo.db;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    /**
     * Конструктор по умолчанию
     */
    public Notification(){

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;

    @Column(name = "notification_value")
    private String value;

    @Column(name = "notification_type")
    private String type;
}
