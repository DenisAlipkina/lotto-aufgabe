package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {
    private final String logClassName = "CSVReader";
    private LottoLogger logger;
    private String filepath;
    private BufferedReader reader;
    private final static String delimiter = ",";

    
    public CSVReader(LottoLogger logger) {
        this.logger = logger;
        logger.logInit(logClassName, "CSVReader");
        logger.logInit(logClassName, "CSVReader completed");
    }

    //could be transported into constructor
    private void init(String filepath) {
        logger.logInit(logClassName, "Creating file- and bufferedreader");
         this.filepath = filepath;
        try {
            FileReader fileReader = new FileReader(this.filepath);
            this.reader = new BufferedReader(fileReader);
            logger.logInit(logClassName, "Created file- and bufferedreader");
        } catch (FileNotFoundException e) {
            logger.logError(logClassName, "Failed to created file- and bufferedreader. " + e);
            //Bad exceptionhandeling
            e.printStackTrace();
        }
         
    }

    public List<Integer> read(String filepath) {
        String line = "";
        List<Integer> token = new LinkedList<>();
        init(filepath);
        try {
            logger.logMessage(logClassName, "Starting to read from " + filepath);
            line = reader.readLine();
            logger.logMessage(logClassName, "Reading completed. Result: " + line);
            if(line != null) {
                //token.addAll(Arrays.asList(line.split(delimiter)).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList()));
                for(String num : line.split(delimiter)) {
                    token.add(Integer.valueOf(num));
                }
            }
        } catch (IOException e) {
            logger.logError(logClassName, "Failed to read the file: " + filepath + ". " + e);
            //Bad exceptionhandeling
            e.printStackTrace();
        }   
        logger.logMessage(logClassName, "Reading and converting into list completed. Result: " + token); 
        return token;
    }
}
