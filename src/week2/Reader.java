package week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader extends Thread {

    private String fileName;
    private ArrayList<String> wordList = new ArrayList<>();

    public Reader(String fileName){
        this.fileName = fileName;
    }

    public int getWordCount(){
        return wordList.size();
    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<String> getWords(){
        return wordList;
    }
    @Override
    public void run() {

        System.out.println("Thread parsing " + fileName + "...");
        File file = new File("C:\\Users\\C505\\IdeaProjects\\untitled\\src\\com\\company\\" + fileName +".txt");
        try(Scanner sc = new Scanner(new FileInputStream(file))){
            while(sc.hasNext()){
                String word = sc.next();
                if(!wordList.contains(word)){
                    wordList.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
