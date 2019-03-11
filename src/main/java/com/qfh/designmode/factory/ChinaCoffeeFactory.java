package com.qfh.designmode.factory;

/*
 * @Description 中国咖啡厂生产卡布奇诺和拿铁
 * @Author alvin
 * @Date 2019-03-11 16:13:51
 */
public class ChinaCoffeeFactory extends CoffeeFactory {
    @Override
    public Coffee[] createCoffee() {
        return new Coffee[]{new Cappuccino(),new Lattee()};
    }
}
