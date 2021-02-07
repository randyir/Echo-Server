package MultiTCPEchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    
    public ClientHandler(Socket socket) {
        this.client = socket;
        
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            String received;
            do {
               received = in.readLine();
                System.out.println(received);
                out.println("ECHO : " + received); 
            } while (!received.equals("quit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (client != null) {
                    System.out.println("Closing down connection");
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}