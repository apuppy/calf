package com.demo.calf.concurrent;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ConcurrentDemo {

    public static void main(String[] args) {
        // stopByFlag(); // 停止
        // pauseByFlag(); // 暂停/恢复
        // jumpQueueByJoin(); // 插队
        // stopByInterrupt(); // 唤醒
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

    static void jumpQueueByJoin() {
        RunnableC rc = new RunnableC();
        Thread t = new Thread(rc);
        t.start();
        try {
            TimeUnit.SECONDS.sleep(5);
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("终于轮到我了");
    }

    static void stopByInterrupt() {
        RunnableD rd = new RunnableD();
        Thread t = new Thread(rd);
        t.start();
        try {
            // sleep(5000);
            TimeUnit.SECONDS.sleep(5);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
