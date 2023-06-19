package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    private String filepath;
    private BufferedReader reader;
    private final static String delimiter = ",";

    /**
     * Initializing BufferedReader
     * @param filepath path to csv-file for initializing FileReader
     */
    private void init(String filepath) {
         this.filepath = filepath;
        try {
            FileReader fileReader = new FileReader(this.filepath);
            this.reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

    public List<Integer> read(String filepath) {
        String line = "";
        List<Integer> token = new LinkedList<>();
        init(filepath);
        try {
            line = reader.readLine();
            if(line != null) {
                //token.addAll(Arrays.asList(line.split(delimiter)).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList()));
                for(String num : line.split(delimiter)) {
                    token.add(Integer.valueOf(num));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
        return token;
    }
}
