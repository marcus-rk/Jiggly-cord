package com.example.javafx_jigglycord.controllers;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class UserController {
    private File userFile;
    private String username;
    private String email;
    private String password;
    private boolean isOnline;

    public UserController(File userFile, String username,String email, String password) throws IOException {
        this.userFile = userFile;
        this.username = username;
        this.email = email;
        this.password = password;

        try {
            writeToUserFile("email:"+email+",",userFile);
            writeToUserFile("password:"+password+",",userFile);
            writeToUserFile("isOnline:"+false+",",userFile);
            System.out.println("User: "+username+" has been created!");
        } catch (IOException e) {
            System.out.println("Failed to save user");
        }
    }

    // Overload
    private UserController(File userFile, String username,String email,boolean isOnline) throws IOException {
        this.userFile = userFile;
        this.username = username;
        this.email = email;
        this.isOnline = isOnline;
    }
    public String getUsername() {
        return username;
    }

    protected static UserController getUserFromFile(@NotNull File userFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String readUsername = userFile.getName().replace(".txt","");
        String readEmail = null;
        boolean readIsOnline = false;

        try{
            String line;
            while ((line=reader.readLine())!=null){
                if (line.contains("email:")){
                    line=line.replace("email:","");
                    int lastIndex = line.indexOf(',');
                    readEmail = line.substring(0,lastIndex);
                }
                if (line.contains("isOnline:")){
                    line=line.replace("isOnline:","");
                    int lastIndex = line.indexOf(',');
                    readIsOnline = line.substring(0,lastIndex).equals("true");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new UserController(userFile,readUsername,readEmail,readIsOnline);
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

//    // TODO
//    private void replaceInUserFile(String tag, String text,File file)throws IOException {
//        String username = file.getName().replace(".txt","");
//        String email = UserController.getUserFromFile(file).email;
//        String password = UserController.getUserFromFile(file).password;
//        boolean isOnline = UserController.getUserFromFile(file).isOnline;
//        File newFile = new File("src/main/resources/users/"+username+".txt");
//    }


//    /**
//     * Changes isOnline for user to true or false
//     * @param onlineStatus if
//     * @throws IOException
//     */
//    protected void setIsOnline(boolean onlineStatus) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(userFile));
//
//        // SHOULD BE VALIDMATCH()
//        if(validMatch()){
//            String line;
//            while ((line=reader.readLine())!=null){
//                if (line.contains("isOnline:")){
//                    line=line.replace("isOnline:","");
//                    int lastIndex = line.indexOf(',');
//
//                    String newLine = line.substring(0,lastIndex);
//
//                    if (newLine.equals("false") && onlineStatus){
//                        isOnline = true;
//                        replaceInUserFile("isOnline:","true",userFile);
//                    } else if (newLine.equals("true") && !onlineStatus){
//                        isOnline = false;
//                        replaceInUserFile("isOnline:","false",userFile);
//                    }
//
//                }
//            }
//        }
//    }

    /**
     * Check if userFile/username matches with password
     * @return true if correct
     * @throws IOException ...
     */
    private boolean validMatch() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        boolean passwordMatch = false;

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
