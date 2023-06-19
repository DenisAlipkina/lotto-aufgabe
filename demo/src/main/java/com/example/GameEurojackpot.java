package com.example;

import java.util.List;

public class GameEurojackpot extends Game {

    private final int amountOfTips = 5;
    private final int amountChoosingFrom = 50;

    private final int amountOfJackpotTips = 2;
    private final int amountJackpotChoosingFrom = 10;

    public GameEurojackpot(List<Integer> excludedUnluckyNumbers) {
        super(excludedUnluckyNumbers);
    }

    @Override
    public String generateTip() {
        return generateTip(amountOfTips, amountChoosingFrom).toString() + " | " + generateTip(amountOfJackpotTips, amountJackpotChoosingFrom).toString();
    }
}