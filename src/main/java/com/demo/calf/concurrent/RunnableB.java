package com.demo.calf.concurrent;

import java.util.concurrent.TimeUnit;

public class RunnableB implements Runnable {

    private volatile boolean pause;

    /**
     * 暂停执行
     */
    void tellToPause() {
        pause = true;
    }

    /**
     * 恢复执行
     */
    void tellToResume() {
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void run() {
        System.out.println("进入不可暂停区域 1……");
        doingLongTime(5);
        System.out.println("退出不可暂停区域 1……");
        System.out.printf("检测标志pause = %s", String.valueOf(pause));

        if (pause) {
            System.out.println("暂停执行");
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("恢复执行");
        }

        System.out.println("进入不可暂停区域 2……");
        doingLongTime(3);
        System.out.println("退出不可暂停区域 2……");
    }

    /**
     * 执行业务代码
     *
     * @param seconds delay seconds
     */
    private void doingLongTime(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("doing something……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
