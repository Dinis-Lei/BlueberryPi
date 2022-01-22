package com.ies.blueberry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user", nullable = false, unique = true)
    private String user;

    @Column(name = "pass", nullable = false)
    private String pass;

    public User(){}

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
