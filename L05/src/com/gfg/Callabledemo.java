package com.gfg;

import java.util.concurrent.*;

public class Callabledemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //
                //
                return "msg-1 from server1";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> stringFuture =  executorService.submit(callable1);

        System.out.println(stringFuture.get());

        System.out.println("Done");

    }
}
