package com.gfg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class VisitorRecord{
    int count;

    public synchronized void increment(){
        int readValue = count;
        System.out.println("Value:"+readValue);
        count=readValue+1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

public class RaceDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Map<String,String> map = new HashMap<>();

        CountDownLatch countDownLatch = new CountDownLatch(100);
        VisitorRecord visitorRecord = new VisitorRecord();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executed in thread: "+Thread.currentThread().getName());
                map.put("msg",Thread.currentThread().getName());
                visitorRecord.increment();
                countDownLatch.countDown();
            }
        };

        for(int i=0; i<100; i++){
            executorService.submit(runnable);
        }


        countDownLatch.await();

        System.out.println("Done final msg:"+map.get("msg"));
        System.out.println("Visitors:"+visitorRecord.getCount());
    }
}
