package chat;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Client{

    private String host;
    private int port;
    private String userName;



   public Client(String host, int port){
        this.port = port;
        this.host = host;
    }


    public void connect() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("You are now connected");

            ExecutorService threadExecutor = Executors.newCachedThreadPool();

            threadExecutor.execute(new ReadThread(socket, this));
            threadExecutor.execute(new WriteThread(socket, this));



            //============//CONTROVERSIAL//=============//
            threadExecutor.shutdown();

        }catch(UnknownHostException e) {
            System.out.println("Host " + host + "not found" + e.getMessage());
        }catch (IOException e) {
            System.out.println("I/O error occured");
        }
    }

    public void setUserName(String username){
        this.userName = username;
    }

    public String getUserName(){
        return this.userName;
    }

    public static void main(String[] args){
        String hostname;
        hostname = (args.length == 0) ? "localhost" : args[0];

        Client client = new Client(hostname, 10001);
        client.connect();
    }

    /*

    public void sendMessage(String message) throws IOException{

        FileWriter write = new FileWriter("messages.txt", true);                // Read input stream from server and output said message.
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
    }                // Await user input via System.in (standard input stream).
                BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
                // Save input message/
                Message = userInputBR.readLine();

                // Send message to server via output stream.
                writer.println(Message);

     */

}
