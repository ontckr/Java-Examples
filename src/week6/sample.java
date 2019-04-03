package week6;

import java.net.*;

public class sample {
    public static void main(String argv[]) throws UnknownHostException {
        InetAddress addr;
        try {
            addr = InetAddress.getByName("www.turkiye.gov.tr");
            System.out.println(addr.getHostAddress());
            addr = InetAddress.getByName("94.55.118.33");
            System.out.println(addr.getHostName());
        } catch (UnknownHostException e) {
        }

        InetAddress[] google = InetAddress.getAllByName("www.javatpoint.com");

        for (InetAddress address : google) {
            System.out.println(address.getHostAddress());
        }
    }
}