package com.gfg.iostreams;

import java.io.*;

public class ObjectIODemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        writeObjectInFile();
        readObjectFromFile();
    }

    public static void writeObjectInFile() throws IOException {
        Product product = new Product("Laptop",40000.00);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/tmp/objectFile.txt"));
        outputStream.writeObject(product);
        outputStream.close();
    }

    public static void readObjectFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/tmp/objectFile.txt"));
        Product product = (Product) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(product);
    }

}
