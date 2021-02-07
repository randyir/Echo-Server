package TCPEchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    
    public static void main(String[] args) {
        System.out.println("Opening Port " + PORT + " ...\n");
        
        try {
            servSock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Unable to attach to port");
            System.exit(1);
        }
        do {
            run();
        } while (true);
    }
    private static void run() {
        Socket link = null;
        try {
            link = servSock.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);
            int numMessages = 0;
            String message = in.readLine();
            
            while (!message.equals("close")) {
                System.out.println("Message received");
                numMessages++;
                out.println("Message" + numMessages + ": " + message);
                message = in.readLine();
            }
            out.println(numMessages + " message received.");
        } catch (IOException e) {
            e.printStackTrace();
    }
    finally {
            try {
                System.out.println("******* Closing Connection *******");
            } catch (Exception e) {
                System.out.println("Unable to disconnect");
                System.exit(1);
            }
        }
    }
}