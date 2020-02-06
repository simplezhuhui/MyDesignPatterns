package com.coder.singleton;


/**
 * 单例模式演化过程
 * 3.懒汉式，先不初始化实例对象，需要的时候再创建。
 * 缺点：有线程安全问题,并不能保证，继续改进。。
 */
public class Singleton03 {
    private static Singleton03 INSTANCE;

    private Singleton03() {
    }

    public static Singleton03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton03.getInstance().hashCode());
            }, "thread" + i).start();
        }
    }
}
