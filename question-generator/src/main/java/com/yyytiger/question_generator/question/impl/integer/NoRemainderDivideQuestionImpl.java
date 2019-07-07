package com.yyytiger.question_generator.question.impl.integer;

import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.random.RandomHelper;

public class NoRemainderDivideQuestionImpl extends AbstractOneStepQuestion {
    private int number1;
    private int number2;
    private IntegerOperator operator;

    public NoRemainderDivideQuestionImpl(int n, int m) {
        this.operator = IntegerOperator.DIVIDE;

        //Make sure non-zero divisor
        this.number2 = RandomHelper.getRandomInt(n, 1);

        int result = RandomHelper.getRandomInt(m);

        this.number1 = this.number2 * result;
    }

    @Override
    protected IntegerOperator getOperator() {
        return this.operator;
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
