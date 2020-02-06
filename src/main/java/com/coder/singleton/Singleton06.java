package com.coder.singleton;

/**
 * 单例模式演化过程
 * 6.懒汉式，先不初始化实例对象，需要的时候再创建。
 * 缺点：有线程安全问题,并不能保证
 * 通过synchronized,加锁解决,但导致效率下降，继续改进，试图通过锁的细化，尽量锁住少的代码块来保证.
 * 但不可行，因为假如有个线程在1判断后，还没进入同步代码块，另一个线程抢先进入同步代码块中并且new出来一个实例，
 * 释放锁后，原先线程再进入的话，又会重复new对象。继续改进为经典的DCL双重检查,进入同步代码块后再判断一次，
 * 但还需要用volatile修饰变量，禁止指令重排，到这一步，差不多了，接下来再看别的实现。
 *
 */
public class Singleton06 {
    private static volatile Singleton06 INSTANCE;

    private Singleton06() {
    }

    public static Singleton06 getInstance() {
        if (INSTANCE == null) {//1
            synchronized (Singleton06.class) {
                if(INSTANCE == null){//2
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton06.getInstance().hashCode());
            }, "thread" + i).start();
        }
    }
}
