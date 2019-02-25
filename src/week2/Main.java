package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Thread {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        ArrayList<Reader> threads = new ArrayList<>();
        int distinctWords = 0;
        for (String arg : args) {
            Reader reader = new Reader(arg);
            threads.add(reader);
            reader.run();
        }

        for (Reader reader : threads) {
            reader.join();
            distinctWords += reader.getWordCount();
            String seachkey = "HELLO";
            if(reader.getWords().contains(seachkey)){
                System.out.println(seachkey + " exists in " + reader.getFileName());
            }

        }
        System.out.println("Threads are complete. Aggregating results.");
        System.out.println("There are "+ distinctWords + " distinct words in files file1, file2, file3.");


    }
}
