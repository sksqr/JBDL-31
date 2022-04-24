package com.gfg.iostreams;

import java.io.*;
import java.util.Scanner;

public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        String filePath = "/tmp/test.txt";
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(filePath);
            while (inputStream.available() > 0){
                char c = (char) inputStream.read();
                System.out.print(c);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        readDataFromFile(filePath);

    }


    public static void readDataFromFile(String path) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream(path));
        System.out.println();
        while (scanner.hasNext()){
            String readData = scanner.nextLine();
            System.out.print(readData);
        }
    }
}
