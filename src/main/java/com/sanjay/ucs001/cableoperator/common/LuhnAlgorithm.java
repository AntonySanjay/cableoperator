package com.sanjay.ucs001.cableoperator.common;

import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Luhn Algorithm or mod10 to verify the validity of the card
 */
@Component
public class LuhnAlgorithm {

    public Boolean validCard(Long card) {
        String cardString = getCardString(card);
        int length = cardString.length();

        if (length != 16) {
            return false;
        }

        long[] numbers = new long[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = Long.parseLong(cardString.substring(i, i + 1));
        }

        for (int i = length - 2; i >= 0; i -= 2) {
            long currentNumber = numbers[i];
            currentNumber *= 2;
            if (currentNumber > 9) {
                currentNumber = currentNumber % 10 + 1;
            }
            numbers[i] = currentNumber;
        }
        long sum = this.calculateSum(numbers);

        return sum % 10 == 0;
    }

    private String getCardString(Long card) {
        return String.valueOf(card);
    }

    private long calculateSum(long[] values) {
        return Arrays.stream(values).sum();
    }

}
