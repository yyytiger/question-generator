package com.yyytiger.question_generator.test.question.integer;

import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.question.impl.integer.NoRemainderDivideQuestionImpl;
import com.yyytiger.question_generator.test.Helper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NoRemainderDivideQuestionImplTest {
    @Test
    public void testThreeThree() {
        testNoRemainderDivideQuestionImpl(3, 3);
    }

    @Test
    public void testThreeTwo() {
        testNoRemainderDivideQuestionImpl(3, 2);
    }

    @Test
    public void testTwoTwo() {
        testNoRemainderDivideQuestionImpl(2, 2);
    }

    @Test
    public void testTwoOne() {
        testNoRemainderDivideQuestionImpl(2, 1);
    }

    @Test
    public void testOneOne() {
        testNoRemainderDivideQuestionImpl(1, 1);
    }

    private void testNoRemainderDivideQuestionImpl(int n, int m){
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Question question = new NoRemainderDivideQuestionImpl(n, m);
            questions.add(question);
        }
        Helper.printQuestions(questions);
    }
}
