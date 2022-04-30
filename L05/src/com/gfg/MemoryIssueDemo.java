package com.gfg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Indicator{

    private volatile boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

public class MemoryIssueDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String,String> map = new HashMap<>();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Indicator indicator = new Indicator();
        indicator.setFlag(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i=1;
                while (indicator.isFlag()){
                    System.out.println("Thread:"+Thread.currentThread().getName()+ " i="+1);
                    i++;
                }
                countDownLatch.countDown();
            }
        };

        executorService.submit(runnable);

        Thread.sleep(1000);

        indicator.setFlag(false);

        System.out.println("Done");


    }
}
