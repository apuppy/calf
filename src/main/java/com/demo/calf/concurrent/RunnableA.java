package com.demo.calf.concurrent;

import java.util.concurrent.TimeUnit;

public class RunnableA implements Runnable {

    private volatile boolean stop;

    /**
     * 停止
     */
    void tellToStop() {
        stop = true;
    }

    @Override
    public void run() {
        System.out.println("进入不可停止区域 1……");
        doingLongTime(5);
        System.out.println("退出不可停止区域 1……");
        System.out.printf("检测标志stop = %s \n", String.valueOf(stop));

        if (stop) {
            System.out.println("停止执行");
            return;
        }

        System.out.println("进入不可停止区域 2……");
        doingLongTime(3);
        System.out.println("退出不要执行区域 2……");
    }

    /**
     * 执行业务代码
     *
     * @param seconds delay seconds
     */
    void doingLongTime(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("doing something ……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
