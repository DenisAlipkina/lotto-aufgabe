package com.example;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LottoModel {
    private final String filePath;
    private List<Integer> unluckyNumbers;
    private final CSVReader reader;
    private final CSVWriter writer;
    private final Game lotto;
    private final Game eurolotto;

    public LottoModel(String filePath) {
        this.filePath = filePath;
        reader = new CSVReader();
        writer = new CSVWriter();
        readUnluckyNumbers();
        lotto = new GameLotto(unluckyNumbers);
        eurolotto = new GameEurojackpot(unluckyNumbers);
    }

    public String generateLottoTip() {
        lotto.excludedUnluckyNumbers = unluckyNumbers;
        return lotto.generateTip();
    }

    public String generateEurojackpotTip() {
        eurolotto.excludedUnluckyNumbers = unluckyNumbers;
        return eurolotto.generateTip();
    }

    public String getUnluckyNumbers() {
        readUnluckyNumbers();
        return unluckyNumbers.toString();
    }

    private void readUnluckyNumbers() {
        unluckyNumbers = reader.read(filePath);
    }
    
    public void deleteNumbers() throws IOException {
        unluckyNumbers = new LinkedList<>();
        writer.write(filePath, unluckyNumbers);
    }

    public boolean addNumber(int num) throws IOException {
        if(!unluckyNumbers.contains(num) && num > 0 && (num <= GameLotto.amountChoosingFrom || num <= GameEurojackpot.amountChoosingFrom)) {
            unluckyNumbers.add(num);
            sortUnluckyNumbers();
            writer.write(filePath, unluckyNumbers);
            return true;
        }
        return false;
    }
    public void deleteNumber(int num) throws IOException {
        int index = unluckyNumbers.indexOf(num);
        if(index >= 0) {
            unluckyNumbers.remove(unluckyNumbers.indexOf(num));
            writer.write(filePath, unluckyNumbers);
        }
       
    }

    private void sortUnluckyNumbers() {
        Collections.sort(unluckyNumbers);
    }

    public Game getGameLotto() {
        return lotto;
    }

    public Game getGameEurojackpot() {
        return eurolotto;
    }
    
}
