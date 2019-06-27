package com.yyytiger.question_generator.calculator.impl;

import com.yyytiger.question_generator.calculator.IntegerCalculator;
import com.yyytiger.question_generator.model.IntegerCalculateResult;

public class MultiCalculatorImpl implements IntegerCalculator {
    @Override
    public IntegerCalculateResult calculate(int number1, int number2) {
        return new IntegerCalculateResult(number1 * number2);
    }
}
