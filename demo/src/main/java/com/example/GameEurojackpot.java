package com.example;

import java.util.List;

public class GameEurojackpot extends Game {

    public final static int amountOfTips = 5;
    public final static int amountChoosingFrom = 50;

    public final static int amountOfJackpotTips = 2;
    public final static int amountJackpotChoosingFrom = 10;

    public GameEurojackpot(List<Integer> excludedUnluckyNumbers, LottoLogger logger) {
        super(excludedUnluckyNumbers, logger);
    }

    @Override
    public String generateTip() {
        return generateTip(amountOfTips, amountChoosingFrom).toString() + " | " + generateTip(amountOfJackpotTips, amountJackpotChoosingFrom).toString();
    }
}