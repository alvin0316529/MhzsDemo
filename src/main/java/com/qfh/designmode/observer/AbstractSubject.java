package com.qfh.designmode.observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject{
    Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> eunmo = vector.elements();
        while (eunmo.hasMoreElements()){
            eunmo.nextElement().update();
        }
    }
}
