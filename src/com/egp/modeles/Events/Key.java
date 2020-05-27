package com.egp.modeles.Events;

import com.egp.constants.enums.Type;

public class Key extends Event{
    private final Type element;

    public Key(Type element){
        super(element.toString());
        this.element = element;
    }

    public Type getElement() { return this.element; }
}
