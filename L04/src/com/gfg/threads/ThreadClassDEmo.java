package com.gfg.threads;

class MyThread extends Thread{

    public void run(){
        System.out.println("Thread name:"+Thread.currentThread().getName());
    }

}

public class ThreadClassDEmo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started");
        //Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        myThread.setName("mythread-0");
       // myThread.run();
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();

        myThread.join();
        myThread2.join();

        System.out.println("Done");
    }
}
