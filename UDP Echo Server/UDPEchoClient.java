package UDPEchoServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;
    
    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (Exception e) {
            System.out.println("Host ID not found");
            System.exit(1);
        }
        run();
    }
    
    private static void run() {
        try {
            dgramSocket = new DatagramSocket();
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
            String message = "", response = "";
            do {
                System.out.print("Enter message: ");
                message = userEntry.readLine();
                if (!message.equals("CLOSE")) {
                    outPacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT);
                    dgramSocket.send(outPacket);
                    buffer = new byte[256];
                    inPacket = new DatagramPacket(buffer, buffer.length);
                    dgramSocket.receive(inPacket);
                    response = new String(inPacket.getData(), 0, inPacket.getLength());
                    System.out.println("SERVER ~ " + response);
                }
            } while (!message.equals("CLOSE"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing Connection");
            dgramSocket.close();
        }
    }
}