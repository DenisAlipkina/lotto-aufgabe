package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LottoLogger {
    private String filepath = "demo/src/log/";


    public LottoLogger() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
        LocalDateTime now = LocalDateTime.now();
        filepath += "log_" + dtf.format(now) + ".txt";
        File file = new File(filepath);
        try {
            FileWriter fw = new FileWriter(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void logInit(String message) {
        write("Initialisation:\t" + message + "\n");
    }

    public void logUserinput(String message) {
        write("Userinput:\t" + message + "\n");
    }

    public void logAddingNumber(String message) {
        write("Adding Number:\t" + message + "\n");
    }

    public void logDeletingNumber(String message) {
        write("Deleting Number:\t" + message + "\n");
    }

    public void logGeneratingTip(String message) {
        write("Generating Tip:\t" + message + "\n");
    }


    private void write(String message) {
        try {
            Files.write(Path.of(filepath), message.getBytes(StandardCharsets.UTF_8),
	                StandardOpenOption.APPEND);
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
        }
        
    }

}
