package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private final String logClassName = "CSVWriter";

    private LottoLogger logger;
    private String filepath;
    private FileWriter writer;
    private final static String delimiter = ",";

    public CSVWriter(LottoLogger logger) {
        this.logger = logger;
        logger.logInit(logClassName, "CSVWriter");
        logger.logInit(logClassName, "CSVWriter completed");
    }

    //could be transported into constructor
    private void init(String filepath) {
        logger.logInit(logClassName, "Creating file at: " + filepath);
         this.filepath = filepath;
            File file;
            try {
                file = new File(this.filepath);
                this.writer = new FileWriter(file);
                logger.logInit(logClassName, "Creating file at: " + this.filepath);
            } catch (IOException e) {
                logger.logError(logClassName, "Failed to created file. " + e);
                //Bad exceptionhandeling
                e.printStackTrace();
            }
            
         
    }

    public void write(String filepath, List<Integer> unluckyNumbers) throws IOException {
        String line = "";
        init(filepath);
        for(int num : unluckyNumbers) {
            line += num + delimiter;
        }
        if(line.endsWith(",")) {
            line = line.substring(0, line.length()-1);
        }
        logger.logMessage(logClassName, "Starting to write: " + line);
        writer.write(line);
        logger.logMessage(logClassName, "Writing completed: " + line);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.logError(logClassName, "Failed to sleep. " + e);
            //Bad exceptionhandeling
            e.printStackTrace();
        }
        writer.close();
    }
}
