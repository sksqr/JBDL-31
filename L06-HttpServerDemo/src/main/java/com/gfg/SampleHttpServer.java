package com.gfg;

import com.sun.net.httpserver.HttpServer;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SampleHttpServer {

  public static void main(String[] args) {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
      server.createContext("/app", new  MyHttpHandler());
      server.createContext("/hello", new HelloHandler());
      server.setExecutor(Executors.newFixedThreadPool(10));
      server.start();
      System.out.println(" Server started on port 8001");
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
