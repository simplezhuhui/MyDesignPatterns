package com.coder.singleton;

/**
 * 单例模式演化过程
 * 1.饿汉式，在类加载完成后，就进行对象实例化。而且JVM可以保证线程安全，仅实例化一个单例。
 * 缺点就是，不管有没有用到，类加载完后就已经初始化了（吹毛求疵）。
 */
public class Singleton01 {
    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {}

    public static Singleton01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton01 instance1 = Singleton01.getInstance();
        Singleton01 instance2 = Singleton01.getInstance();
        System.out.println(instance1 == instance2);//true

    }
}
