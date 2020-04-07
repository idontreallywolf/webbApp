package com.webapp.model;

public class RegisterForm {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;

    public void setFirstname(String firstname){ this.firstname = firstname; }
    public void setLastname(String lastname){ this.lastname = lastname; }
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }
    public void setPasswordConfirm(String password){ this.passwordConfirm = password; }
    public void setEmail(String email){ this.email = email; }

    public String getFirstname(){ return this.firstname; }
    public String getLastname(){ return this.lastname; }
    public String getUsername(){ return this.username; }
    public String getPassword(){ return this.password; }
    public String getPasswordConfirm(){ return this.passwordConfirm; }
    public String getEmail(){ return this.email; }
}
