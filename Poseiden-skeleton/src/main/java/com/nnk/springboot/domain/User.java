package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(allocationSize=1, schema="myschema",  name="MySequenceGenerator", sequenceName = "mysequence")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "MySequenceGenerator")
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Column(length = 125)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Column(length = 125)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Column(length = 125)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Column(length = 125)
    private String role;

    public User(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
