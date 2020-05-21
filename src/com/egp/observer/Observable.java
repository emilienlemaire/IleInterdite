package com.egp.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Observable {
    private final List<Observer> observers;

    public Observable() {
        this.observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void clearObserver() {
        this.observers.clear();
    }

    public void notifyObservers() {
        for (Observer observer :
                observers) {
            observer.update();
        }
    }
}
