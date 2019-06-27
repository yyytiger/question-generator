package com.yyytiger.question_generator.question.impl.integer;

import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.question.Question;

public abstract class AbstractOneStepQuestion implements Question {

    protected abstract IntegerOperator getOperator();

    protected abstract int getNumber1();

    protected abstract int getNumber2();

    @Override
    public String getQuestionText() {
        return String.format("%s %s %s = ", getNumber1(), getOperator().getText(), getNumber2());
    }

    @Override
    public String getAnswerText(){
        return getOperator().calculate(getNumber1(), getNumber2()).getDisplayText();
    }
}
