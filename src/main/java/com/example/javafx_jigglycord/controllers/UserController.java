package com.example.javafx_jigglycord.controllers;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Controller class which responsibility is to create users and process user infomation.
 */
public class UserController extends Controller {
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
            writeToUserFile("email:"+email+",",userFile);
            writeToUserFile("password:"+password+",",userFile);
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
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String readUsername = userFile.getName().replace(".txt","");
        String readEmail = null;

        try{
            String line;
            while ((line=reader.readLine())!=null){
                if (line.contains("email:")){
                    line=line.replace("email:","");
                    int lastIndex = line.indexOf(',');
                    readEmail = line.substring(0,lastIndex);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new UserController(userFile,readUsername,readEmail);
    }

    /**
     * Function that makes it easier to write to a txt file
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
