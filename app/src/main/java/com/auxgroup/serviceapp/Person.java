package com.auxgroup.serviceapp;

/**
 * Created by Administrator on 2015-10-29.
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [name="+name+"]";
    }
}
