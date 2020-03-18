package com.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @Column(name="id")        private int id;
    @Column(name="firstname") private String firstname;
    @Column(name="lastname")  private String lastname;
    @Column(name="username")  private String username;
    @Column(name="email")     private String email;

    public int    getId()        { return this.id; }
    public String getFullName()  { return this.firstname+" "+this.lastname; }
    public String getFirstname() { return this.firstname; }
    public String getLastname()  { return this.lastname; }
    public String getUsername()  { return this.username; }
    public String getEmail()     { return this.email; }
    public String toString()     { return getId()+" | "+getFullName()+" | "+getUsername()+" | "+getEmail(); }

    public void setId(int id) { this.id = id; }
    public void setFirstname(String fname) { this.firstname = fname; }
    public void setLastname(String lname)  { this.firstname = lname; }
    public void setUsername(String uname)  { this.username = uname;  }
    public void setEmail(String email)     { this.email = email;     }
}
