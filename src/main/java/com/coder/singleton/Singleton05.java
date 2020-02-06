package com.coder.singleton;

/**
 * 单例模式演化过程
 * 5.懒汉式，先不初始化实例对象，需要的时候再创建。
 * 缺点：有线程安全问题,并不能保证
 * 通过synchronized,加锁解决,但导致效率下降，继续改进，试图通过锁的细化，尽量锁住少的代码块来保证.
 * 但很显然还是有线程问题，继续改进。。。
 */
public class Singleton05 {
    private static Singleton05 INSTANCE;

    private Singleton05() {
    }

    public static Singleton05 getInstance() {
        if (INSTANCE == null) {//1
            synchronized (Singleton05.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton05.getInstance().hashCode());
            }, "thread" + i).start();
        }
    }
}
