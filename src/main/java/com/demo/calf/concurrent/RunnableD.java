package com.demo.calf.concurrent;

import static java.lang.Thread.sleep;

public class RunnableD implements Runnable {

    @Override
    public void run() {
        System.out.println("进入暂停……");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("收到中断异常……");
            System.out.println("做一些相关处理……");
        }
        System.out.println("继续执行或选择退出……");
    }

}
