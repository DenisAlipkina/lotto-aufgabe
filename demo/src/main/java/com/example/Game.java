package com.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Game implements TipGeneration {
    private final String logClassName = "Game";
    public LottoLogger logger;

    public List<Integer> excludedUnluckyNumbers;

    public Game(List<Integer> excludedUnluckyNumbers, LottoLogger logger) {
        this.logger = logger;
        logger.logInit(logClassName, "Game");
        this.excludedUnluckyNumbers = excludedUnluckyNumbers;
        logger.logInit(logClassName, "Game completed with this unluckyNumbers: " + excludedUnluckyNumbers);
    }

    public abstract String generateTip();

    public List<Integer> generateTip(int amountOfTips, int amountChoosingFrom) {
        logger.logGeneratingTip(logClassName, "Starting generating tip with " + amountOfTips + " amountOfTips and " + amountChoosingFrom + " amountChoosingFrom");
        /*
            List<Integer> numbers = Stream.iterate(1, n -> n + 1)
                              .limit(amountChoosingFrom)
                              .filter(n -> !excludedUnluckyNumbers.contains(n))
                              .collect(Collectors.toList());
         */
        List<Integer> numbers = new LinkedList<>();
        logger.logGeneratingTip(logClassName, "Starting to create list of possible numbers. List: " + numbers);
        for (int i = 1; i <= amountChoosingFrom; i++) {
            if(!excludedUnluckyNumbers.contains(i)) {
                numbers.add(i);
            }
        }
        logger.logGeneratingTip(logClassName, "Created list of possible numbers. List: " + numbers);
        logger.logGeneratingTip(logClassName, "Starting to shuffle list of possible numbers. List: " + numbers);
        Collections.shuffle(numbers);
        logger.logGeneratingTip(logClassName, "Shuffled list of possible numbers. List: " + numbers);
        if (numbers.size() >= amountOfTips) {
            logger.logGeneratingTip(logClassName, "Starting to choose first " + amountOfTips + " tips from List: " + numbers);
            List<Integer> tips = numbers.subList(0, amountOfTips);
            logger.logGeneratingTip(logClassName, "Tips were selected. Tips: " + tips);
            Collections.sort(tips);
            logger.logGeneratingTip(logClassName, "Sorting tips completed. Tips: " + tips);
            logger.logGeneratingTip(logClassName, "Returning tips: " + tips);
            return tips;
        } else {
            logger.logGeneratingTip(logClassName, "Not enough numbers to choose from. Possible tips: " + numbers);
            Collections.sort(numbers);
            logger.logGeneratingTip(logClassName, "Sorting tips completed. Tips: " + numbers);
            logger.logGeneratingTip(logClassName, "Returning tips: " + numbers);
            return numbers;
        }
        
    }
}
