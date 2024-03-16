package com.mohanad.springpractice.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebUser {
    @NotNull(message = "Please enter a employee id")
    private Integer id;
    @NotBlank(message = "Please enter a username")
    @Size(min = 5, max = 20, message = "Username must be 5-20 characters long.")
    private String userName;
    @NotBlank(message = "Please enter a password")
    @Size(min = 8, max = 16, message = "Password must be 8-16 characters long.")
    private String password;
    @NotBlank(message = "Please enter an email")
    @Email
    private String email;

    public WebUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
