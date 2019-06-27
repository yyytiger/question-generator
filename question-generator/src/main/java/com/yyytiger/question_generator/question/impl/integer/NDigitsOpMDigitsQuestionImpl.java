package com.yyytiger.question_generator.question.impl.integer;

import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.random.RandomHelper;

public class NDigitsOpMDigitsQuestionImpl extends AbstractOneStepQuestion {
    private int number1;
    private int number2;
    private IntegerOperator operator;

    public NDigitsOpMDigitsQuestionImpl(IntegerOperator operator, int n, int m) {
        this.operator = operator;

        //Make sure non-zero divisor
        this.number2 = RandomHelper.getRandomInt(m, 1);

        //Make sure non-negative result and dividable
        if (operator == IntegerOperator.MINUS || operator == IntegerOperator.DIVIDE)
            this.number1 = RandomHelper.getRandomInt(n, this.number2);
        else
            this.number1 = RandomHelper.getRandomInt(n);
    }

    @Override
    protected IntegerOperator getOperator() {
        return operator;
    }

    @Override
    protected int getNumber1() {
        return this.number1;
    }

    @Override
    protected int getNumber2() {
        return this.number2;
    }
}
