package com.historicalclub.entity;

public class Hero {

    private long id;
    private String name;

    public Hero(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
