package com.egp.modeles.Events;

public class Event {
    private String name;

    public Event(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {return this.name; }
}
