package com.example;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LottoModel {
    private final String filePath = "demo/src/resources/unluckyNumbers.csv";
    private List<Integer> unluckyNumbers;
    private final CSVReader reader;
    private final CSVWriter writer;

    public LottoModel() {
        unluckyNumbers = new LinkedList<>();
        reader = new CSVReader();
        writer = new CSVWriter();
    }

    public String getUnluckyNumbers() {
        readUnluckyNumbers();
        return "Test";
    }

    private void readUnluckyNumbers() {
        unluckyNumbers = reader.read(filePath);
    }

    public void addNumber(int num) throws IOException {
        if(!unluckyNumbers.contains(num)) {
            unluckyNumbers.add(num);
            sortUnluckyNumbers();
            writer.write(filePath, unluckyNumbers);
        } 
    }

    private void sortUnluckyNumbers() {
        Collections.sort(unluckyNumbers);
    }
}
