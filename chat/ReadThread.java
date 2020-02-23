package chat;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadThread implements Runnable {
    private BufferedReader reader;
    private Socket socket;
    private Client client;

    public ReadThread(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;

       try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



    public void run(){
        while(true){
            try {
                String response = reader.readLine();

                // prints the username and timestamp before displaying the server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + getTimeStamp()+ "] " + response +"\n");
                }
            } catch (IOException ex) {
                System.out.println("You left the Chat");
                break;
            }
        }
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
