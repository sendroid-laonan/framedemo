package com.sendroids.framedemo.entity;

import groovy.util.logging.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="town")
@Slf4j
public class Town extends BaseEntity{

    @Id
    @GeneratedValue
    private  Long id;


    @Column(name = "name",nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "manager",nullable = false)
    private String manager;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}
