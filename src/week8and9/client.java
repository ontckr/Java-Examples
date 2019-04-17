package week8and9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws Exception {

        try (Socket socket = new Socket("127.0.0.1", 53000)) {
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            //Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (in.hasNextLine()) {
                // System.out.println(in.nextLine());

                StringBuilder content = new StringBuilder();

                // many of these calls can throw exceptions, so i've just
                // wrapped them all in one try/catch statement.
                try
                {
                    // create a url object
                    URL url = new URL(in.nextLine());

                    // create a urlconnection object
                    URLConnection urlConnection = url.openConnection();

                    // wrap the urlconnection in a bufferedreader
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String line;

                    // read from the urlconnection via the bufferedreader
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        content.append(line + "\n");
                    }
                    bufferedReader.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


                String[] words = content.toString().split("\\s+");
                System.out.println("Word Count: " + words.length);
                out.println(words.length);


            }
        }
    }

}