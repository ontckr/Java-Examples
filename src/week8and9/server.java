package week8and9;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {

    private static ArrayList<Socket> sockets = new ArrayList<>();

    private static ArrayList<String> urls = new ArrayList<>();

    private static boolean isSent = false;

    public static void main(String[] args) throws Exception {

        urls.add("https://www.cs.umb.edu/~smimarog/textmining/datasets/r8-train-all-terms.txt");
        urls.add("https://www.cs.umb.edu/~smimarog/textmining/datasets/r8-test-all-terms.txt");
        urls.add("https://www.cs.umb.edu/~smimarog/textmining/datasets/r52-train-all-terms.txt");
        urls.add("https://www.cs.umb.edu/~smimarog/textmining/datasets/r52-test-all-terms.txt");

        try (ServerSocket listener = new ServerSocket(59898)) {
            System.out.println("The URL server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(4);
            while (true) {
                pool.execute(new URLMaster(listener.accept()));
            }
        }
    }

    public static void sendURL() {
        for (int i = 0; i < 4; i++) {

            System.out.println("Send to: " + sockets.get(i).getPort());
            try {
                PrintWriter out = new PrintWriter(sockets.get(i).getOutputStream(), true);
                out.println(urls.get(i));
            } catch (Exception e) {
                System.out.println(e);
            } finally {
              /*  try { sockets.get(i).close(); } catch (IOException e) {}
                System.out.println("Closed: " + sockets.get(i));*/
            }
        }
    }

    private static class URLMaster implements Runnable {
        private Socket socket;

        URLMaster(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            System.out.println("Connected: " + socket);

            sockets.add(socket);

            if (sockets.size() == 4) {
                System.out.println("4 CLIENTS!!!");
                sendURL();
            }

            try {
                Scanner in = new Scanner(socket.getInputStream());
                //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //while (in.hasNextLine()) {
                System.out.println(in.nextLine());
                // }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("c");
            }
        }
    }

}
