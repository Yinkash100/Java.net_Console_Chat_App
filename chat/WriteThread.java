package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WriteThread implements Runnable{
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String userName = in.nextLine();
        Console console = System.console();

//        String userName = console.readLine("\nEnter your name: ");
        client.setUserName(userName);
        writer.println(userName);

        String text;
        System.out.println("Welcome " + userName + "\nEnter any message and hit enter to send");
        do {
            text = in.nextLine();
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }

}
