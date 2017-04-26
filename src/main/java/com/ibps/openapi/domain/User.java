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
    @Column(length=40)
    @GeneratedValue(generator="randomId")
    @GenericGenerator(name="randomId", strategy="com.ibps.openapi.domain.RandomIdGenerator")
    private String id;

    private String userId ;
    private String email ;
    private String username ;

    private String firstName ;

    private String lastName ;

    private String locale ;

}
