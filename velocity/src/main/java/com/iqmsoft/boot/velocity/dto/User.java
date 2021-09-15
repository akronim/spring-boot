package com.iqmsoft.boot.velocity.dto;

import com.iqmsoft.boot.velocity.enums.Gender;

public class User {
    private String firstName;
    private String lastName;
    private Gender sex;
    private String username;
    private String password;
    private String role;


    public User(String firstName, String lastName, Gender sex, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
