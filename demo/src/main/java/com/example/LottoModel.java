package com.example;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LottoModel {
    private final String logClassName = "LottoModel";

    private LottoLogger logger;
    private final String filePath;
    private List<Integer> unluckyNumbers;
    private final CSVReader reader;
    private final CSVWriter writer;
    private final Game lotto;
    private final Game eurolotto;
    public final int maxAmountUnluckyNumbers = 6;

    public LottoModel(String filePath, LottoLogger logger) {
        this.logger = logger;
        logger.logInit(logClassName, "LottoModel");
        this.filePath = filePath;
        reader = new CSVReader(logger);
        writer = new CSVWriter(logger);
        readUnluckyNumbers();
        lotto = new GameLotto(unluckyNumbers, logger);
        eurolotto = new GameEurojackpot(unluckyNumbers, logger);
        logger.logInit(logClassName, "LottoModel complete");
    }

    public String generateLottoTip() {
        logger.logGeneratingTip(logClassName, "Starts generating tip for lotto");
        lotto.excludedUnluckyNumbers = unluckyNumbers;
        return lotto.generateTip();
    }

    public String generateEurojackpotTip() {
        logger.logGeneratingTip(logClassName, "Starts generating tip for eurojackpot");
        eurolotto.excludedUnluckyNumbers = unluckyNumbers;
        return eurolotto.generateTip();
    }

    public List<Integer> getUnluckyNumbers() {
        readUnluckyNumbers();
        return unluckyNumbers;
    }

    private void readUnluckyNumbers() {
        unluckyNumbers = reader.read(filePath);
    }
    
    public void deleteNumbers() throws IOException {
        logger.logDeletingNumber(logClassName, "All numbers will be deleted. List: " + unluckyNumbers);
        unluckyNumbers = new LinkedList<>();
        logger.logDeletingNumber(logClassName, "All numbers are deleted. List: " + unluckyNumbers);
        writer.write(filePath, unluckyNumbers);
    }

    public boolean addNumber(int num) throws IOException {
        logger.logAddingNumber(logClassName, num + " will be added. List: " + unluckyNumbers);
        if(!unluckyNumbers.contains(num) && num > 0 && (num <= GameLotto.amountChoosingFrom || num <= GameEurojackpot.amountChoosingFrom) && unluckyNumbers.size() < maxAmountUnluckyNumbers) {
            unluckyNumbers.add(num);
            logger.logAddingNumber(logClassName, num + " is added. List: " + unluckyNumbers);
            sortUnluckyNumbers();
            writer.write(filePath, unluckyNumbers);
            return true;
        }
        logger.logAddingNumber(logClassName, num + " couldnt be added. List: " + unluckyNumbers);
        //multiple if cases for more specific loggs would be nice
        logger.logAddingNumber(logClassName, num + " is already in list or is an illegal number");
        return false;
    }
    public void deleteNumber(int num) throws IOException {
        logger.logDeletingNumber(logClassName, num + " will be deleted. List: " + unluckyNumbers);
        int index = unluckyNumbers.indexOf(num);
        if(index >= 0) {
            unluckyNumbers.remove(unluckyNumbers.indexOf(num));
            logger.logDeletingNumber(logClassName, num + " is deleted. List: " + unluckyNumbers);
            writer.write(filePath, unluckyNumbers);
        } else {
            logger.logDeletingNumber(logClassName, num + " couldnt be deleted. List: " + unluckyNumbers);
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
