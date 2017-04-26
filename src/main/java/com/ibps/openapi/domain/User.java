package com.ibps.openapi.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;


/**
 * Created by amit on 4/25/17.
 */
@Entity
public class User {
    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "randomId")
    @GenericGenerator(name = "randomId", strategy = "com.ibps.openapi.domain.RandomIdGenerator")
    private String id;
    private String email;
    private String username;

    private String firstName;

    private String lastName;

    private String locale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
