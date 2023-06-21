package com.example;

import java.util.List;


public class GameLotto extends Game {

    public final static int amountOfTips = 6;
    public final static int amountChoosingFrom = 49;

    public GameLotto(List<Integer> excludedUnluckyNumbers, LottoLogger logger) {
        super(excludedUnluckyNumbers, logger);
    }

    @Override
    public String generateTip() {
        return generateTip(amountOfTips, amountChoosingFrom).toString();
    }
}
