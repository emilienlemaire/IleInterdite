package com.egp.modeles;

import com.egp.constants.enums.Type;

public class Key {
    private final Type element;

    public Key(Type element){
        this.element = element;
    }

    public Type getElement() { return this.element; }
}
