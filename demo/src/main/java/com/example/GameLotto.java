package com.example;

import java.util.List;


public class GameLotto extends Game {

    private final int amountOfTips = 6;
    private final int amountChoosingFrom = 49;

    public GameLotto(List<Integer> excludedUnluckyNumbers) {
        super(excludedUnluckyNumbers);
    }

    @Override
    public String generateTip() {
        return generateTip(amountOfTips, amountChoosingFrom).toString();
    }
}
