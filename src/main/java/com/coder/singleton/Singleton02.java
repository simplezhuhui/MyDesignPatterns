package com.coder.singleton;

/**
 * 单例模式演化过程
 * 2.饿汉式另一种写法，利用静态代码块完成初始化，和第一种无本质区别。
 */
public class Singleton02 {
    private static final Singleton02 INSTANCE;

    static {
        INSTANCE = new Singleton02();
    }

    private Singleton02() {
    }

    public static Singleton02 getINSTANCE() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton02 instance1 = Singleton02.getINSTANCE();
        Singleton02 instance2 = Singleton02.getINSTANCE();
        System.out.println(instance1 == instance2);
    }
}
