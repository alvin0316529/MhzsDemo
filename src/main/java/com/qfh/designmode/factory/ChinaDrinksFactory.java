package com.qfh.designmode.factory;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-11 17:10:11
 */
public class ChinaDrinksFactory implements AbstractDrinkFactory {
    @Override
    public Coffee createCoffee() {
        return new Lattee();
    }

    @Override
    public Tea createTea() {
        return new Tea();
    }

    @Override
    public Sodas createSodas() {
        return null;
    }
}
