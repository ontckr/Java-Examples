package week3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Thread {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        HashMap<String, ArrayList<String>> words = new HashMap<String, ArrayList<String>>();


        ArrayList<Reader> threads = new ArrayList<>();
        int distinctWords = 0;
        for (String arg : args) {
            Reader reader = new Reader(arg, words);
            threads.add(reader);
            reader.run();
        }

        for (Reader reader : threads) {
            reader.join();
        }


        for (ArrayList<String> wordList : words.values()) {
            distinctWords+= wordList.size();
        }

        String seachkey = "onat";

        for (String i : words.keySet()) {

            if(words.get(i).contains(seachkey)){
                System.out.println(seachkey + " found in " + i);
            }
        }
        System.out.println("Threads are complete. Aggregating results.");
        System.out.println("There are "+ distinctWords + " distinct words in files file1, file2, file3.");


    }
}