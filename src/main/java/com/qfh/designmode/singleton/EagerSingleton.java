package com.qfh.designmode.singleton;

/*
 * @Description 饿汉式单例类
 * @Author alvin
 * @Date 2019-03-11 10:42:55
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){};

    public static EagerSingleton getInstance(){
        return instance;
    }
}
