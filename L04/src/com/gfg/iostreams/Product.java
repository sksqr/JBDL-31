package com.gfg.iostreams;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable  {

    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        String data = "name:"+this.name+", price:"+this.price;
        out.write(data.getBytes());
    }

//    private void readObject(ObjectInputStream in)   throws IOException, ClassNotFoundException{
//
//    }

}
