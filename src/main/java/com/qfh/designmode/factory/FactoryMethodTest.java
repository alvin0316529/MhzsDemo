package com.qfh.designmode.factory;

import org.junit.Test;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-11 16:21:28
 */
public class FactoryMethodTest {
    public void print(Coffee[] coffees){
        for (Coffee c : coffees){
            System.out.println(c.getName());
        }
    }


    @Test
    public void coffeeTest(){
        CoffeeFactory chinaCoffeeFactory = new ChinaCoffeeFactory();
        Coffee[] chinaCoffees = chinaCoffeeFactory.createCoffee();
        System.out.println("中国咖啡厂可以生产的咖啡有：");
        print(chinaCoffees);

        CoffeeFactory americaCoffeeFactory = new AmericaCoffeeFactory();
        Coffee[] americaCoffees = americaCoffeeFactory.createCoffee();
        System.out.println("美国咖啡厂可以生产的咖啡有：");
        print(americaCoffees);
    }

    @Test
    public void drinkFactoryTest(){
        AbstractDrinkFactory chinaFactory = new ChinaDrinksFactory();
        Coffee coffee = chinaFactory.createCoffee();
        Tea tea = chinaFactory.createTea();
        Sodas sodas = chinaFactory.createSodas();
        System.out.println("中国咖啡：" + coffee.getName());
        System.out.println("中国茶：" + tea.getName());
        //System.out.println("中国饮料：" + sodas.getName());

        AbstractDrinkFactory americaFactory = new AmericaDrinksFactory();
        Coffee coffee1 = americaFactory.createCoffee();
        Tea tea1 = americaFactory.createTea();
        Sodas sodas1 = americaFactory.createSodas();

        System.out.println("美国咖啡：" + coffee1.getName());
       // System.out.println("美国茶：" + tea1.getName());
        System.out.println("美国饮料：" + sodas1.getName());
    }
























}
