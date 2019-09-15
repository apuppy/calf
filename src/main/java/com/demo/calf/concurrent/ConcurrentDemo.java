package com.demo.calf.concurrent;

import java.util.concurrent.TimeUnit;

public class ConcurrentDemo {

    public static void main(String[] args) {
        stopByFlag(); // 停止
        // pauseByFlag(); // 暂停/恢复
    }

    static void stopByFlag() {
        RunnableA ra = new RunnableA();
        new Thread(ra).start();
        ra.tellToStop();
    }

    static void pauseByFlag() {
        RunnableB rb = new RunnableB();
        new Thread(rb).start();
        rb.tellToPause();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rb.tellToResume();
    }

}
