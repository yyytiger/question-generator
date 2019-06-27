package com.yyytiger.question_generator.random;

import java.util.Random;

public class RandomHelper {
    public static int getRandomInt(int numberOfDigits) {
        return getRandomInt(numberOfDigits, 0);
    }

    public static int getRandomInt(int numberOfDigits, int minLower) {
        checkNumberOfDigits(numberOfDigits);

        int lower;
        if (numberOfDigits == 1)
            lower = 0;
        else
            lower = (int) Math.pow(10, numberOfDigits - 1);
        if (lower < minLower)
            lower = minLower;

        int upper = (int) Math.pow(10, numberOfDigits) - 1;

        return nextInt(lower, upper);
    }

    public static int nextInt(int lower, int upper) {
        if (lower > upper)
            throw new IllegalArgumentException(String.format("lower cannot be greater than upper for nextInt, lower: %s, upper %s", lower, upper));
        Random random = new Random();
        int result = random.nextInt(upper - lower);
        return result + lower;
    }

    private static void checkNumberOfDigits(int numberOfDigits){
        if (numberOfDigits <= 0)
            throw new IllegalArgumentException(String.format("numberOfDigits must be greater than zero, numberOfDigits %s", numberOfDigits));
        if (numberOfDigits > 10)
            throw new IllegalArgumentException(String.format("numberOfDigits must be less or equal than ten, numberOfDigits %s", numberOfDigits));
    }
}
