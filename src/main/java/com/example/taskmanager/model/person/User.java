package com.example.taskmanager.model.person;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private Rol rol;
    private Common associated;

    public User(String name, String password, Rol rol, Common associated) {
        this.username = name;
        this.password = password;
        this.rol = rol;
        this.associated = associated;
    }

    public User() {
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Common getAssociated() {
        return associated;
    }

    public void setAssociated(Common associated) {
        this.associated = associated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
