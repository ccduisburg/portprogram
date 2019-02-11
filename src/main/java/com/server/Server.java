package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=null;
try{
    serverSocket=new ServerSocket(5555);
}catch(IOException e){
    System.err.println("I/O exception: "+e.getMessage());
    System.exit(1);
}
        System.out.println("Server started..waiting to connection..");
        Socket clientSocket=null;
        try{
            clientSocket=serverSocket.accept();
        }catch(IOException e){
            System.out.println("Accept failed.");
            System.exit(1);
        }


        System.out.println(clientSocket.getLocalAddress()+"connected.");
        PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        System.out.println("waiting for client input...");
        while((inputLine=in.readLine())!=null){
            System.out.println("from client..:"+inputLine );
            outputLine=inputLine.toUpperCase();
            out.println(outputLine);
            if(outputLine.equals("BYE"))
                break;

        }
        System.out.println(clientSocket.getLocalAddress() +" connection closed");
        out.close();
        in.close();
    clientSocket.close();
    serverSocket.close();


    }


}


