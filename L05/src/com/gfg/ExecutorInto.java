package com.gfg;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorInto {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executed in thread: "+Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);


//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done "+Thread.currentThread().getName());

    }
}
