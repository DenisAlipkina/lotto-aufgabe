package com.example;

import java.util.LinkedList;
import java.util.List;

public class LottoModel {
    private List<Integer> unluckyNumbers;
    private CSVReader reader;

    public LottoModel() {
        unluckyNumbers = new LinkedList<>();
        reader = new CSVReader();
    }

    public String getUnluckyNumbers() {
        readUnluckyNumbers();
        return "Test";
    }

    private void readUnluckyNumbers() {
        unluckyNumbers = reader.read("demo/src/resources/unluckyNumbers.csv");
    }
}
