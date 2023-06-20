package com.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Game implements TipGeneration{

    public List<Integer> excludedUnluckyNumbers;

    public Game(List<Integer> excludedUnluckyNumbers) {
        this.excludedUnluckyNumbers = excludedUnluckyNumbers;
    }

    public abstract String generateTip();

    public List<Integer> generateTip(int amountOfTips, int amountChoosingFrom) {
        /*
            List<Integer> numbers = Stream.iterate(1, n -> n + 1)
                              .limit(amountChoosingFrom)
                              .filter(n -> !excludedUnluckyNumbers.contains(n))
                              .collect(Collectors.toList());
         */
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= amountChoosingFrom; i++) {
            if(!excludedUnluckyNumbers.contains(i)) {
                numbers.add(i);
            }
        }
        Collections.shuffle(numbers);
        if (numbers.size() >= amountOfTips) {
            List<Integer> tips = numbers.subList(0, amountOfTips);
            Collections.sort(tips);
            return tips;
        } else {
            Collections.sort(numbers);
            return numbers;
        }
        
    }
}
