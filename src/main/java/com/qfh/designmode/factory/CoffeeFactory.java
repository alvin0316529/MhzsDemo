package com.qfh.designmode.factory;

/*
 * @Description 定义一个抽象咖啡工厂
 * @Author alvin
 * @Date 2019-03-11 16:10:07
 */
public abstract class CoffeeFactory {
    /**
     * 生产可制造的咖啡
     * @return
     */
    public abstract Coffee[] createCoffee();
}
