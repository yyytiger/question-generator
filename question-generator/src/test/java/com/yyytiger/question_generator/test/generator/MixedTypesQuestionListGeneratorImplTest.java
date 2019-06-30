package com.yyytiger.question_generator.test.generator;

import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.generator.impl.MixedTypesQuestionListGeneratorImpl;
import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.question.impl.integer.NDigitsOpMDigitsQuestionImpl;
import com.yyytiger.question_generator.test.Helper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MixedTypesQuestionListGeneratorImplTest {
    @Test
    public void testMixedTypes(){
        List<QuestionGenerator> questionGenerators = Arrays.asList(
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3)
        );

        MixedTypesQuestionListGeneratorImpl generator = new MixedTypesQuestionListGeneratorImpl(100, questionGenerators);
        Helper.printQuestions(generator.generateQuestions());
    }
}
