package MultiTCPEchoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiTCPEchoServer {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    
    public static void main(String[] args) {
        System.out.println("Opening Port " + PORT + " ..... \n");
        try {
            servSock = new ServerSocket(PORT);
            do {
                Socket client = servSock.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
            } while (true);
        } catch (IOException e) {
            System.out.println("Unable to attach to port");
            System.exit(1);
        }
    }
}