package com.qfh.designmode.factory;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-11 17:11:15
 */
public class AmericaDrinksFactory implements AbstractDrinkFactory {
    @Override
    public Coffee createCoffee() {
        return new Cappuccino();
    }

    @Override
    public Tea createTea() {
        return null;
    }

    @Override
    public Sodas createSodas() {
        return new Sodas();
    }
}
