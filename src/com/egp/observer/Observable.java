package com.egp.observer;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        for (Observer observer :
                observers) {
            observer.update();
        }
    }
}
