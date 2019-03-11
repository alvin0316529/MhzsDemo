package com.qfh.designmode.factory;

public interface AbstractDrinkFactory {
    /**
     * 制造咖啡
     * @return
     */
    Coffee createCoffee();

    /**
     * 制造茶
     * @return
     */
    Tea createTea();

    /**
     * 制造碳酸饮料
     * @return
     */
    Sodas createSodas();
}
