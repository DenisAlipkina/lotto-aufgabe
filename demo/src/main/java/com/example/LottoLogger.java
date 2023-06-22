package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LottoLogger {
    private String filepath = "demo/src/log/";

    private boolean logWriting = true;

    public LottoLogger(boolean logWriting) {
        this.logWriting = logWriting;
        if(this.logWriting) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
            LocalDateTime now = LocalDateTime.now();
            filepath += "log_" + dtf.format(now) + ".txt";
            File file = new File(filepath);
            try {
                //for creating file in filepath
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException e) {
                //Bad exception handeling
                e.printStackTrace();
            }
        }
    }

    public void logInit(String className, String message) {
        write("Initialisation:\t" + className + ": " + message + "\n");
    }

    public void logUserinput(String className, String message) {
        write("Userinput:\t" + className + ": " + message + "\n");
    }

    public void logAddingNumber(String className, String message) {
        write("Adding Number:\t" + className + ": " + message + "\n");
    }

    public void logDeletingNumber(String className, String message) {
        write("Deleting Number:\t" + className + ": " + message + "\n");
    }

    public void logGeneratingTip(String className, String message) {
        write("Generating Tip:\t" + className + ": " + message + "\n");
    }

    public void logSelectingGame(String className, String message) {
        write("Game selected:\t" + className + ": " + message + "\n");
    }

    public void logError(String className, String message) {
        write("Error:\t" + className + ": " + message + "\n");
    }

    public void logMessage(String className, String message) {
        write("Message:\t" + className + ": " + message + "\n");
    }


    private void write(String message) {
        if(this.logWriting) {
            try {
                Files.write(Path.of(filepath), message.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                //bad exceptionhandeling
            }
        }
    }

}
