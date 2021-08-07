package com.example.javafx_jigglycord.controllers;

import java.io.*;

public class UserController {
    private File userFile;
    private String username;
    private String email;
    private String password;

    public UserController(File userFile, String username,String email, String password) throws IOException {
        this.userFile = userFile;
        this.username = username;
        this.password = password;

        try {
            writeToUserFile("email:"+email+",",userFile);
            writeToUserFile("password:"+password+",",userFile);
            System.out.println("User: "+username+" has been created!");
        } catch (IOException e) {
            System.out.println("Failed to save user");
        }
    }

    /**
     * Function that makes it easier to write to a .txt file
     * @param text text to be added
     * @param file file wished to write to
     * @throws IOException ...
     */
    private void writeToUserFile(String text, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        printWriter.println(text);

        // Can be done for global writers maybe
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }


}
