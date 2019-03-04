package sample;

public class main {
    public static void main(String [] args ){
        Thread mythread = new mythread();
        mythread.start();
    }

    public static class mythread extends Thread{
        @Override
        public void run(){
            System.out.print("hello");
        }
    }
}
