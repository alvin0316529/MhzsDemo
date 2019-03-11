package com.qfh.designmode.singleton;

/*
 * @Description  懒汉式单例类
 * @Author alvin
 * @Date 2019-03-11 10:36:51
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton(){};

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
