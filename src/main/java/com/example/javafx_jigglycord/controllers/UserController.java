package com.example.javafx_jigglycord.controllers;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Controller class which responsibility is to create users and process user infomation.
 */
public class UserController extends FileManager {
    private File userFile;
    private String username;
    private String email;
    private String password;

    public UserController(File userFile, String username,String email, String password) throws IOException {
        this.userFile = userFile;
        this.username = username;
        this.email = email;
        this.password = password;

        try {
            super.writeToUserFile("email:"+email+",",userFile);
            super.writeToUserFile("password:"+password+",",userFile);
            System.out.println("User: "+username+" has been created!");
        } catch (IOException e) {
            System.out.println("Failed to save user");
        }
    }

    // Overload
    private UserController(File userFile, String username,String email) throws IOException {
        this.userFile = userFile;
        this.username = username;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    protected static UserController getUserFromFile(@NotNull File userFile) throws IOException {
        String readUsername = readTagFromFile(userFile,Tag.USERNAME);
        String readEmail = readTagFromFile(userFile,Tag.EMAIL);

        return new UserController(userFile,readUsername,readEmail);
    }

    /**
     * Check if userFile/username matches with password
     * @return true: if userfile matches with currentUsers password
     *         false: if userfile do not match with currentUsers password
     * @throws IOException StackTrace
     */
    protected boolean validMatch(File userFile) throws IOException {
        UserController currentUser = super.getCurrentUserController();
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        boolean passwordMatch = false;

        String password = currentUser.password;

        try{
            String line;
            while ((line=reader.readLine())!=null){
                if (line.contains("password:")){
                    line=line.replace("password:","");
                    int lastIndex = line.indexOf(',');

                    String newLine = line.substring(0,lastIndex);
                    if (newLine.matches(password)) passwordMatch=true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return passwordMatch;
    }

}
