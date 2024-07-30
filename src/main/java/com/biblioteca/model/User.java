package com.biblioteca.model;

import java.util.Objects;

public class User {
    private int id;
    String name;
    String password;
    String email;

    public User(String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) { this.id = id;}

    public void setName(String name) { this.name = name; }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id= " + id +
                ", name=' " + name + '\'' +
                ", email=' " + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, email);
    }
}
