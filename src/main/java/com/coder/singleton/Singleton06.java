package com.coder.singleton;

/**
 * 单例模式演化过程
 * 6.懒汉式，先不初始化实例对象，需要的时候再创建。
 * 缺点：有线程安全问题,并不能保证
 * 通过synchronized,加锁解决,但导致效率下降，继续改进，试图通过锁的细化，尽量锁住少的代码块来保证.
 * 但很显然还是有线程问题，继续改进为经典的DCL双重检查,进入同步代码块后再判断一次，
 * 但还需要用volatile修饰变量，禁止指令重排
 * （INSTANCE = new Singleton06()：这一句会分成三条指令，1申请内存空间2初始化3内存地址赋值给引用，这三步可能会发生重排。
 * 有可能排序为132，申请好空间后，直接赋值给引用，此时内存地址已经不为null,其他线程此时已经可以读到，然后直接返回，产生问题）
 * ，到这一步，差不多了，接下来再看别的实现。
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
