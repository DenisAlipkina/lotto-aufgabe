package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private String filepath;
    private FileWriter writer;
    private final static String delimiter = ",";

    private void init(String filepath) {
         this.filepath = filepath;
            File file;
            try {
                file = new File(this.filepath);
                this.writer = new FileWriter(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
         
    }

    public void write(String filepath, List<Integer> unluckyNumbers) throws IOException {
        String line = "";
        init(filepath);
        for(int num : unluckyNumbers) {
            line += num + delimiter;
        }
        //if statement for empty line should be added
        
        line = line.substring(0, line.length()-1);
        System.out.println(line);
        writer.write(line);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        writer.close();
    }
}
