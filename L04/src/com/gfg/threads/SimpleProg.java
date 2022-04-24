package com.gfg.threads;

import java.util.Scanner;

public class SimpleProg {

    public static void main(String[] args) {
        System.out.println("Enter name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Hello "+name);
    }
}
