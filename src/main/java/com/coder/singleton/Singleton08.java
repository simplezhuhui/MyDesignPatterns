package com.coder.singleton;

/**
 * 单例模式演化过程
 * 大神（effective java 作者）的单例实现
 * 使用枚举类，线程安全，实现优雅...（工作中反正没用过，看看就行）
 */
public enum Singleton08 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton08.INSTANCE.hashCode());
            }, "thread" + i).start();
        }
    }
}
