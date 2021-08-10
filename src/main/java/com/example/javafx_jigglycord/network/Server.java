package com.example.javafx_jigglycord.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server extends Thread{
    private static Server server;
    private String ip;
    private int port;
    private String endTag;

    @Override
    public void run() {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        // Socket to run as server
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---SERVER STARTED---");

        while (true){

            try {

                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                // TODO: MAKE CLIENT MANAGER
                while (true){

                    String messageFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + messageFromClient);

                    bufferedWriter.write("Message Received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messageFromClient != null && messageFromClient.equals(endTag))
                        break;

                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

                System.out.println("---SERVER ENDED---");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Server getServer(){
        if(server == null){
            server = new Server();
        }
        return server;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getEndTag() {
        return endTag;
    }

    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }

}
