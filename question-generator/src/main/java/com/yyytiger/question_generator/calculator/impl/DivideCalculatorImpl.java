package com.yyytiger.question_generator.calculator.impl;

import com.yyytiger.question_generator.calculator.IntegerCalculator;
import com.yyytiger.question_generator.model.IntegerCalculateResult;

public class DivideCalculatorImpl implements IntegerCalculator {
    @Override
    public IntegerCalculateResult calculate(int number1, int number2) {
        if(number2 == 0)
            throw new IllegalArgumentException(String.format("number2 cannot be zero, number2: %s", number2));
        return new IntegerCalculateResult(number1 / number2, number1 % number2);
    }
}
