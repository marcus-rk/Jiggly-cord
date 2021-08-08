package com.example.javafx_jigglycord.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String clientName;
    private Server server;
    private String serverIp;
    private int serverPort;
    private String endTag;

    public Client(String clientName, Server server) {
        this.clientName = clientName;
        this.server = server;
        this.serverIp = server.getIp();
        this.serverPort = server.getPort();
        this.endTag = server.getEndTag();
    }

    public void startClient() {

        // Communication is between socket-socket
        Socket socket = null;

        // Streams to read/write information coming from socket
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        // Instead of reading byte to byte, you can read/write larger chunks of information
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        try{

            // IP address of server (localhost) and TCP port
            socket = new Socket(serverIp,serverPort);

            // Read from server and output to server.
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Scanner read from keyboard
            Scanner scanner = new Scanner(System.in);

            while (true){

                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine(); // newline so that server knows when message stops
                bufferedWriter.flush(); // Forces buffer to flush

                System.out.println("Server: " + bufferedReader.readLine());

                // Message to send if you want to end connection to server
                if (message.equalsIgnoreCase(endTag))
                    break;
            }

        }  catch (IOException e) {
            e.printStackTrace();
        } finally {

            // Important to make sure to close
            try {
                if(socket != null)
                    socket.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(outputStreamWriter != null)
                    outputStreamWriter.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
