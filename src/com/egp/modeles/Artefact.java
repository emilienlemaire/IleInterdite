package com.egp.modeles;

import com.egp.constants.Type;

public class Artefact {
    private final Type element;

    public Artefact(Type element){
        this.element = element;
    }

    public Type getElement() { return this.element; }
}
