package chat;

import java.io.*;
import java.net.*;

public class UserThread implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            //printUsers();

            String userName = reader.readLine();
            server.addUserName(userName);


            String serverMessage = "New user connected: " + userName;
            System.out.println(userName + " connected");
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                server.broadcast(userName + ":- " + clientMessage, this);

            } while (!clientMessage.equals("bye"));

            //server.removeUser(userName, this);
            System.out.println(userName + " left");
            socket.close();

            serverMessage = userName + " left.";
            server.removeUser(userName, this);
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
    void sendMessage(String message) {
        writer.println(message);
    }
}
