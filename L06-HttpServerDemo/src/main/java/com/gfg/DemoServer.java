package com.gfg;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoServer {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("testing server");

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Enter request data:");
            String data = scanner.nextLine();
            if(data.equals("exit")){
                executorService.shutdown();
                System.exit(0);
            }
            executorService.submit(()->{
                System.out.println("Worker Thread:"+Thread.currentThread().getName()+" Processing Data:"+data);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
/*

while(true){

// Accept http request
// executorService.submit(httRequest);

}
 */
