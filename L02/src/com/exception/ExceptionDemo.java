package com.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionDemo {


    public static void main(String[] args) {

        String name =null;
       String msg = demoMethod(name);
       System.out.println(msg);


//        try {
//            FileReader fileReader = new FileReader("/tmp/test.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        //System.out.println("Done");



    }


    public static void doProcessing() throws UserException {
        //
        throw new UserException();
    }

    public static String demoMethod(String name){
        try {
            System.out.println(name.length());
            doProcessing();
        }
        catch (NullPointerException ex){
            System.out.println(ex.getClass().toString());
            return "Exception NullPointerException";
        } catch (UserException e) {
            e.printStackTrace();
            return "Exception UserException";
        }
        finally {
            System.out.println("Executing finally");
        }
        return "Done";
    }
}
