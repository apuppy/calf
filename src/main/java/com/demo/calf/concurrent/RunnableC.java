package com.demo.calf.concurrent;

import java.util.concurrent.TimeUnit;

public class RunnableC implements Runnable {

    @Override
    public void run() {
        System.out.println("进入不可暂停区域 1……");
        doingLongTime(5);
        System.out.println("退出不可暂停区域 1……");
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
