package sample;

class main implements Runnable{

    private String name;

    public main(String s){
       name =s ;
    }

    String getName(){return name;}

    public void sleep(int time){
        try{
            Thread.sleep(time);
        } catch(InterruptedException e ){
            e.printStackTrace();
        }
    }
    public static void main(String [] args ){
        Thread t1 = new main("t1");
        Thread t2 = new main("t2");
        Thread t3 = new main("t3");

        t1.start();
        t2.start();
        t3.start();

    }

}
