package week4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader extends Thread {

    private String fileName;
    private Map<String, ArrayList<String>> words;


    public Reader(String fileName, Map<String, ArrayList<String>> words) {
        this.fileName = fileName;
        this.words = words;
    }

    @Override
    public void run() {

        System.out.println("Thread parsing " + fileName + "...");

        synchronized (words) {
            words.put(fileName, new ArrayList<String>());
        }

        File file = new File("/Users/onatcakir/Documents/java-labs/src/week4");
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                String word = sc.next();
                synchronized (words) {
                    if (!words.get(fileName).contains(word)) {
                        words.get(fileName).add(word);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}