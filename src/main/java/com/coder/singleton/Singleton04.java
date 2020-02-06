package com.coder.singleton;
/**
 * 单例模式演化过程
 * 4.懒汉式，先不初始化实例对象，需要的时候再创建。
 * 缺点：有线程安全问题,并不能保证
 * 通过synchronized,加锁解决,但导致效率下降，继续改进。。。
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

    private Singleton04() {
    }

    public  static synchronized Singleton04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton04.getInstance().hashCode());
            }, "thread" + i).start();
        }
    }
}
