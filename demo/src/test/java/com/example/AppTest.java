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
        LottoModel model = new LottoModel("src/test/resources/unluckyNumbersTest.csv");
        assertTrue(!model.getGameLotto().excludedUnluckyNumbers.isEmpty());
        assertTrue(!model.getGameEurojackpot().excludedUnluckyNumbers.isEmpty());
        assertEquals(model.getGameLotto().excludedUnluckyNumbers, model.getGameEurojackpot().excludedUnluckyNumbers);
    }

    @Test
    @RepeatedTest(1000)
    public void generated_lottotip_doesnt_contain_excluded_numbers_and_correct_amount_of_tips_test() throws IOException {
        LottoModel model = new LottoModel("src/test/resources/unluckyNumbersTest.csv");
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
        //could fail, if csv has to many numbers
        //Maximum amount of Tips would be smaller than GameLotto.amountOfTips
        assertEquals(amountOfTips, GameLotto.amountOfTips);
        assertFalse(excludedNumberIsPresent);
    }

    @Test
    @RepeatedTest(1000)
    public void generated_eurojackpottip1_doesnt_contain_excluded_numbers_and_correct_amount_of_tips_test() throws IOException {
        LottoModel model = new LottoModel("src/test/resources/unluckyNumbersTest.csv");
        assertTrue(!model.getGameEurojackpot().excludedUnluckyNumbers.isEmpty());
        String tips = model.generateEurojackpotTip();
        boolean excludedNumberIsPresent = false;
        int amountOfTips = 0;

        List<String> firstTip = Arrays.asList(tips.substring(tips.indexOf("[") + 1, tips.indexOf("]")).replaceAll("\\s+","").split(","));
        for (String str : firstTip) {
            amountOfTips++;
            if(model.getGameEurojackpot().excludedUnluckyNumbers.contains(Integer.valueOf(str))) {
                excludedNumberIsPresent = true;
            }
        }
        //could fail, if csv has to many numbers
        //Maximum amount of Tips would be smaller than GameEurojackpot.amountOfTips
        assertEquals(amountOfTips, GameEurojackpot.amountOfTips);
        assertFalse(excludedNumberIsPresent);
    }

    @Test
    @RepeatedTest(1000)
    public void generated_eurojackpottip2_doesnt_contain_excluded_numbers_and_correct_amount_of_tips_test() throws IOException {
        LottoModel model = new LottoModel("src/test/resources/unluckyNumbersTest.csv");
        assertTrue(!model.getGameEurojackpot().excludedUnluckyNumbers.isEmpty());
        String tips = model.generateEurojackpotTip();
        boolean excludedNumberIsPresent = false;
        int amountOfTips = 0;

        List<String> secondTip = Arrays.asList(tips.substring(tips.lastIndexOf("[") + 1, tips.lastIndexOf("]")).replaceAll("\\s+","").split(","));
        for (String str : secondTip) {
            amountOfTips++;
            if(model.getGameEurojackpot().excludedUnluckyNumbers.contains(Integer.valueOf(str))) {
                excludedNumberIsPresent = true;
            }
        }
        assertTrue(amountOfTips >= GameEurojackpot.amountOfJackpotTips);
        assertFalse(excludedNumberIsPresent);
    }

    @ParameterizedTest
    @CsvSource({
        "'1,2,3,4,5'",
        "'20,30,40'"
    })
    //deletingtest would look similar
    public void adding_unluckyNumbers_in_model_test(String input) throws IOException {
        CSVWriter writer = new CSVWriter();
        writer.write("src/test/resources/test.csv", List.of());

        LottoModel model = new LottoModel("src/test/resources/test.csv");
        assertTrue(model.getUnluckyNumbers().isEmpty());

        Arrays.asList(input.split(",")).stream().forEach(num -> {
            try {
                model.addNumber(Integer.valueOf(num));
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        });
        assertTrue(!model.getUnluckyNumbers().isEmpty());
        //toString. optional would be a integerlist as input or converting expected List<String> to List<Integer>
        assertEquals(Arrays.asList(input.split(",")).toString(), model.getUnluckyNumbers().toString());
        
        
    }

    @ParameterizedTest
    @CsvSource({
        "-1","-2","0","51","100"
    })
    //deletingtest would look similar
    public void cant_adding_illegal_unluckyNumbers_in_model_test(Integer input) throws IOException {
        CSVWriter writer = new CSVWriter();
        writer.write("src/test/resources/test.csv", List.of());

        LottoModel model = new LottoModel("src/test/resources/test.csv");
        assertTrue(model.getUnluckyNumbers().isEmpty());

        assertFalse(model.addNumber(input));
        assertTrue(model.getUnluckyNumbers().isEmpty());
    }


}
