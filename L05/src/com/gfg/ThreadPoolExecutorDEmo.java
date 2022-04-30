package com.gfg;

import java.util.concurrent.*;

public class ThreadPoolExecutorDEmo {

    public static void main(String[] args) {
        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;
        ExecutorService executorService =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(4)
                );
        /*
        time1: 10 Req/Sec => 5 worker threads
        time10: 100 Req/Sec => 10 worker threads
        time12: 5 Req/Sec => 10 workers threads
        time15: 5 Req/Sec => 5 workers threads
         */

        CountDownLatch countDownLatch = new CountDownLatch(9);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executed in thread: "+Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        };

        for(int i=0; i<15;i++){
            executorService.submit(runnable);
        }




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
