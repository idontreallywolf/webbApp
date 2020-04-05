package com.webapp.model;

import javax.persistence.Column;
import javax.persistence.Id;

import com.webapp.tools.StringTool;

public class Account {
    @Id
    @Column(name="id")        private int id;
    @Column(name="firstname") private String firstname;
    @Column(name="lastname")  private String lastname;
    @Column(name="username")  private String username;
    @Column(name="password")  private String password;
    @Column(name="email")     private String email;

    public Account(){}

    public Account(String firstname, String lastname, String username, String password, String email){
        setId(1);
        setFirstname(firstname);
        setLastname(lastname);
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    public int    getId()        { return this.id; }
    public String getFullName()  { return this.firstname+" "+this.lastname; }
    public String getFirstname() { return this.firstname; }
    public String getLastname()  { return this.lastname; }
    public String getUsername()  { return this.username; }
    public String getPassword()  { return this.password; }
    public String getEmail()     { return this.email; }
    public String toString()     { return getId()+" | "+getFullName()+" | "+getUsername()+" | "+getPassword()+" | "+getEmail(); }

    public void setId(int id)              { this.id = id; }

    public void setFirstname(String fname) {
    	this.firstname = StringTool.ucFirst(fname);
    }
    public void setLastname(String lname)  {
    	this.lastname = StringTool.ucFirst(lname);
    }

    public void setUsername(String uname)  { this.username = uname;  }
    public void setPassword(String pass)   { this.password = pass;   }
    public void setEmail(String email)     { this.email = email;     }
}
