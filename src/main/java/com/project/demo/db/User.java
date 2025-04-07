package com.project.demo.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @jakarta.persistence.Id
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "UserName")
    private String userName;

}