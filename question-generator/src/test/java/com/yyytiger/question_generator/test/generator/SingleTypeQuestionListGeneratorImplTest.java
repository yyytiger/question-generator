package com.yyytiger.question_generator.test.generator;

import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.generator.impl.SingleTypeQuestionListGeneratorImpl;
import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.question.impl.integer.NDigitsOpMDigitsQuestionImpl;
import com.yyytiger.question_generator.test.Helper;
import org.junit.Test;

public class SingleTypeQuestionListGeneratorImplTest {
    @Test
    public void testTwoMultiThree(){
        QuestionGenerator questionGenerator = () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 3);

        SingleTypeQuestionListGeneratorImpl generator = new SingleTypeQuestionListGeneratorImpl(10, questionGenerator);
        Helper.printQuestions(generator.generateQuestions());
    }
}
