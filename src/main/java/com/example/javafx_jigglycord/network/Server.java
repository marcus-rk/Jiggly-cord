package com.example.javafx_jigglycord.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        // WRITE COMMENT HERE
        ServerSocket serverSocket = null;

        while (true){

            try {

                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true){

                    String messageFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + messageFromClient);

                    bufferedWriter.write("Message Received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messageFromClient.equalsIgnoreCase("BYE BITCH"))
                        break;

                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

}
