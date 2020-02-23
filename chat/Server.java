package chat;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private String host;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public Server(int port){
        this.port = port;
    }


    public void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Chat Server is listening on port " + port);
            ExecutorService threadExecutor = Executors.newCachedThreadPool();

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                threadExecutor.execute(newUser);
            }
        }catch (IOException e){
            System.out.println("Error creating server \n" + e.getMessage());
        }
    }
    public static void main(String[] args) {
        int port = 10001; //Integer.parseInt(args[0]);
        System.out.println(port);

        Server server = new Server(port);
        server.startServer();
    }

    void broadcast(String message, UserThread excludeUser) {
        for (UserThread eachUser : userThreads) {
            if (eachUser != excludeUser) {
                eachUser.sendMessage(message);
            }
        }
    }



    void addUserName(String userName) {
        userNames.add(userName);
    }

    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}





/*// Once client has connected, use socket stream to send a prompt message to client.
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                // Prompt for client.
                printWriter.println("You are now connected.\nEnter a message and hit enter to send.");


                // Get input stream produced by client (to read sent message).
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String output = bufferedReader.readLine();
                out.println("["+getTimestamp() + "]" + output);

                // Output sent message from client.
                printWriter.println(output);

                // Close writer and socket.
                printWriter.close();
                out.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String output = bufferedReader.readLine();
                out.println("["+getTimestamp() + "]" + output);


                ObjectOutputStream output = new ObjectOutputStream( socket.getOutputStream() );
                //PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                //printWriter.println("You are now connected.\nEnter a message and hit enter to send.");
                output.


                // Output message from client.
                System.out.println("[FROM Client] %s" + output);

                // Loop back, awaiting a new client connection.

 */


/*
while (true) {
        // Once client has connected, use socket stream to send a prompt message to client.

        // Get input stream produced by client (to read sent message).
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String output = bufferedReader.readLine();

        // Output sent message from client.
        printWriter.println(output);

        // Close writer and socket.
        printWriter.close();
        socket.close();

        // Output message from client.
        Logging.log(String.format("[FROM Client] %s", output));

        // Loop back, awaiting a new client connection.
        }
//        FileWriter fw = new FileWriter("messages.txt", true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        PrintWriter out = new PrintWriter(bw);

  */