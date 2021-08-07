package com.example.javafx_jigglycord.controllers;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public abstract class FileManager extends Controller{

    enum Tag {
        USERNAME,
        EMAIL,
        PASSWORD // (?)
    }

    /**
     * Function that makes it easier to write to a txt file
     * @param text text to be added
     * @param file file wished to write to
     * @throws IOException ...
     */
    protected void writeToUserFile(String text, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        printWriter.println(text);

        // Can be done for global writers maybe
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    // TODO: Should not be static
    protected static String readTagFromFile(File userFile, Tag tag) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String inputTag = "";
        String resultString = "";

        switch (tag) {
            case USERNAME -> {
                resultString = userFile.getName().replace(".txt", "");
                return resultString;
            }
            case EMAIL -> inputTag = "email:";
        }

        try{
            String line;
            while ((line=reader.readLine())!=null){
                if (line.contains(inputTag)){
                    line=line.replace(inputTag,"");
                    int lastIndex = line.indexOf(',');
                    resultString = line.substring(0,lastIndex);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return resultString;
    }


    protected boolean passwordMatchUser(@NotNull String password, @NotNull File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        boolean passwordMatch = false;

        String line;
        while ((line=reader.readLine())!=null){
            if (line.contains("password:")){
                line=line.replace("password:","");
                int lastIndex = line.indexOf(',');

                line = line.substring(0,lastIndex);
                if (line.matches(password))
                    passwordMatch=true;
            }
        }
        return passwordMatch;
    }


}
