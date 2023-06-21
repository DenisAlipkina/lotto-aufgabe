package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @ParameterizedTest
    @CsvSource({
        "'1,2,3,4,5'",
        "'20,30,40'"
    })
    public void csv_write_and_read_are_equal_and_not_empty_test(String input) throws IOException {
        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter();
        List<Integer> inputAsList = Arrays.asList(input.split(",")).stream().map(s -> Integer.valueOf(s)).toList();
        writer.write("src/test/resources/test.csv", inputAsList);
        List<Integer> outputAsList = reader.read("src/test/resources/test.csv");
        assertTrue(!inputAsList.isEmpty());
        assertTrue(!outputAsList.isEmpty());
        assertEquals(inputAsList, outputAsList);
    }

    @Test
    public void init_model_gives_right_games_with_right_unluckyNumbers_test() throws IOException {
        LottoModel model = new LottoModel("src/resources/unluckyNumbers.csv");
        assertTrue(!model.getGameLotto().excludedUnluckyNumbers.isEmpty());
        assertTrue(!model.getGameEurojackpot().excludedUnluckyNumbers.isEmpty());
        assertEquals(model.getGameLotto().excludedUnluckyNumbers, model.getGameEurojackpot().excludedUnluckyNumbers);
    }

    @Test
    @RepeatedTest(1000)
    public void generated_lottotip_doesnt_contain_excluded_numbers_and_correct_amount_of_tips_test() throws IOException {
        LottoModel model = new LottoModel("src/resources/unluckyNumbers.csv");
        assertTrue(!model.getGameLotto().excludedUnluckyNumbers.isEmpty());
        String tips = model.generateLottoTip();
        boolean excludedNumberIsPresent = false;
        int amountOfTips = 0;
        for (String str : Arrays.asList(tips.substring(1, tips.length()-1).replaceAll("\\s+","").split(","))) {
            amountOfTips++;
            if(model.getGameLotto().excludedUnluckyNumbers.contains(Integer.valueOf(str))) {
                excludedNumberIsPresent = true;
            }
        }
        assertEquals(amountOfTips, GameLotto.amountOfTips);
        assertFalse(excludedNumberIsPresent);
    }

    @Test
    @RepeatedTest(1000)
    public void generated_eurojackpottip_doesnt_contain_excluded_numbers_and_correct_amount_of_tips_test() throws IOException {
        LottoModel model = new LottoModel("src/resources/unluckyNumbers.csv");
        assertTrue(!model.getGameEurojackpot().excludedUnluckyNumbers.isEmpty());
        String tips = model.generateEurojackpotTip();
        boolean excludedNumberIsPresent = false;
        int amountOfTips = 0;

        List<String> firstTip = Arrays.asList(tips.substring(tips.indexOf("[") + 1, tips.indexOf("]")).replaceAll("\\s+","").split(","));
        List<String> secondTip = Arrays.asList(tips.substring(tips.lastIndexOf("[") + 1, tips.lastIndexOf("]")).replaceAll("\\s+","").split(","));
        for (String str : firstTip) {
            amountOfTips++;
            if(model.getGameEurojackpot().excludedUnluckyNumbers.contains(Integer.valueOf(str))) {
                excludedNumberIsPresent = true;
            }
        }
        assertEquals(amountOfTips, GameEurojackpot.amountOfTips);
        assertFalse(excludedNumberIsPresent);

        excludedNumberIsPresent = false;
        amountOfTips = 0;
        for (String str : secondTip) {
            amountOfTips++;
            if(model.getGameEurojackpot().excludedUnluckyNumbers.contains(Integer.valueOf(str))) {
                excludedNumberIsPresent = true;
            }
        }
        assertTrue(amountOfTips >= GameEurojackpot.amountOfJackpotTips);
        assertFalse(excludedNumberIsPresent);
    }
}
