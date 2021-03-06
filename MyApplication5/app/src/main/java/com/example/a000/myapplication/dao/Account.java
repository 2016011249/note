package com.example.a000.myapplication.dao;

public class Account {
    private Long id;
    private String name;

    public Account() {
        super();
    }

    public Account(String name) {
        super();
        this.name = name;

    }

    public Account(Long id, String name) {
        super();
        this.id = id;
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public String toString() {

        return getName();
    }
}
