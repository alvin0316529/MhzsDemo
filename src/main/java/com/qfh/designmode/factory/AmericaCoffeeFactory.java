package com.qfh.designmode.factory;

/*
 * @Description 美国咖啡厂生产美式咖啡和拿铁
 * @Author alvin
 * @Date 2019-03-11 16:19:00
 */
public class AmericaCoffeeFactory extends CoffeeFactory {
    @Override
    public Coffee[] createCoffee() {
        return new Coffee[]{new Americano(),new Lattee()};
    }
}
