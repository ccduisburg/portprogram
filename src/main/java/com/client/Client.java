package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=null;
        PrintWriter out=null;
        BufferedReader in=null;

try{socket=new Socket("127.0.0.1",5555);
    out=new PrintWriter(socket.getOutputStream(),true);
    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

}catch(UnknownHostException e){
    System.err.println("it cann not find Server");
    System.exit(1);
} catch(IOException e){
    System.err.println("I/O Exception; "+e.getMessage());
    System.exit(1);

    }
        System.out.println("Successefully connected to Server");
BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
String userInput;
        System.out.println("Waiting of input .(to connection close: [bye])");
        while(!(userInput=stdIn.readLine()).equals("bye")){
            out.println(userInput);
            System.out.println("Answer from Server: "+in.readLine());

        }
        System.out.println("Connection clossing.");
        out.close();
        in.close();
        stdIn.close();
        socket.close();

    }
}
