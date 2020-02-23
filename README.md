# Java Console Chat Application



A simple console-based chat Application. The application consist of a server a client thread that writes message to the server, a thread that brodcast message from the server and another thread that manages users/clients.

The server sits on the localhost of the user and listens for connections on a specified port. Once a client connects, it creates a new thread and listens for a connection from other potential clients. I created a client that connects to the server by specifying the host and port. Also, I created a user thread that is in charge of receiving input from the client and printing messages to it. The user threads start two new threads a read-thread and a write-thread. The read thread uses java BufferedReader to get user input, it then formats it by adding a sender username and a timestamp before sending it to the user thread who then broadcast it through the server. The write thread uses java PrintWriter reader to read messages coming from the server and print it to each client console.


## Prerequisites
Appliaction can be run on any machine that has JDK/JRE installed.

## BUILDING and RUNNING THE APPLICATION

## With an IDE
The server and Client class has a runnable main method that can be run from any IDE. Load the SRC folder and run the server. The IDE should compile all supporting classes.
To run the client get the IP address of the server and use it as an arguement.

## With Command Prompt or Linux Terminal
First download and open the src folder in terminal then follow these steps

1. Compile the server
`javac chat/Server.java`

2. Compile the Client: 
 `javac chat/Client.java`

3. Compile supporting threads
`javac chat/UserThread.java` to compile Userthread
`javac chat/ReadThread.java` to compile Readthread
`javac chat/WriteThread.java` to compile Writethread

4. To run the server
`java chat/Server`

5. To run the Client
if you are running the client on the same computer as the server, use.
`java chat/Client`  *The client sets host to localhost*.
if the client is running on a different computer, use the Server ip address as hostname. 
`java chat/Client "123.456.789.0"` where 123.456.789.0 is the server ip address.

**The default port for both server and client is 10001. If the port is unavailable, you can change it in the code**
