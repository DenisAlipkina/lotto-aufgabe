package com.example;

import java.io.IOException;
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
        unluckyNumbers.add(num);
        writer.write(filePath, unluckyNumbers);
    }
}
