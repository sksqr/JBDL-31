package com.gfg.threads;

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread Name:"+Thread.currentThread().getName());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread Name:"+Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();


        MyRunnable runnableObj = new MyRunnable();
        Thread thread = new Thread(runnableObj);
        thread.start();

        Thread thread2 = new Thread(runnableObj);
        thread2.start();

//        Thread thread3 = new Thread(()->{
//            System.out.println("Lamda, Thread name:"+Thread.currentThread().getName());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread3.start();

       System.out.println( Thread.activeCount());


//        MyRunnable runnable1 = new MyRunnable();
//        runnable1.run();
//
//        MyRunnable runnable2 = new MyRunnable();
//        runnable2.run();

        Thread.sleep(5);

        Long endTime = System.currentTimeMillis();

        System.out.println("Total time taken:"+(endTime-startTime));
        System.out.println("Done");

    }
}
