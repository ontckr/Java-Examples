package week4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main extends Thread {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, ArrayList<String>> words = Collections.synchronizedMap(new HashMap<String, ArrayList<String>>());


        for (String arg : args) {
            Reader reader = new Reader(arg, words);
            executorService.execute(reader);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            int distinctWords = 0;
            for (ArrayList<String> wordList : words.values()) {
                distinctWords += wordList.size();
            }

            System.out.println("Threads are complete. Aggregating results.");
            System.out.println("There are " + distinctWords + " distinct words in files file1, file2, file3.");


            String seachkey = "onat";

            for (String i : words.keySet()) {

                if(words.get(i).contains(seachkey)){
                    System.out.println(seachkey + " found in " + i);
                }
            }


        } catch (InterruptedException e) {

        }

    }
}