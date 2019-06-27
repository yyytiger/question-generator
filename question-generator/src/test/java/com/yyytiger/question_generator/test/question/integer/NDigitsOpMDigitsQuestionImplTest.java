package com.yyytiger.question_generator.test.question.integer;

import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.question.impl.integer.NDigitsOpMDigitsQuestionImpl;
import com.yyytiger.question_generator.test.Helper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NDigitsOpMDigitsQuestionImplTest {
    @Test
    public void testThreePlusThree() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3);
    }

    @Test
    public void testThreeMinusTwo() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 3, 2);
    }

    @Test
    public void testTwoMultiTwo() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 2);
    }

    @Test
    public void testThreeDivideTwo() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2);
    }

    @Test
    public void testOnePlusOne() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 1, 1);
    }

    @Test
    public void testOneMinusOne() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 1, 1);
    }

    @Test
    public void testOneMultiOne() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 1, 1);
    }

    @Test
    public void testOneDivideOne() {
        testNDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 1, 1);
    }

    private void testNDigitsOpMDigitsQuestionImpl(IntegerOperator operator, int n, int m){
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Question question = new NDigitsOpMDigitsQuestionImpl(operator, n, m);
            questions.add(question);
        }
        Helper.printQuestions(questions);
    }
}
