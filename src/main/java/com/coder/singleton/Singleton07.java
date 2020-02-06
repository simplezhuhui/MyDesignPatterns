package com.coder.singleton;

/**
 * 单例模式演化过程
 * 7.使用静态内部类实现懒加载，而且当外部类被加载到内存中时，内部类并不会加载，单例对象不会被实例化，
 * 只有调用方法时，才会去懒加载，比饿汉式的实现更合理。（个人觉得工作使用中，饿汉式就已经满足基本需求了，简单方便）
 */
public class Singleton07 {
    private Singleton07() {
    }

    private static class Holder {
        private final static Singleton07 INSTANCE = new Singleton07();
    }

    public static Singleton07 getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton07.getInstance().hashCode());
            }, "thread" + i).start();
        }
    }
}
