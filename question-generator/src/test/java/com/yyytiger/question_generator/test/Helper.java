package com.yyytiger.question_generator.test;

import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.question.impl.integer.NDigitsOpMDigitsQuestionImpl;

import java.util.List;

public class Helper {
    public static void printQuestions(List<Question> questions){
        for (Question question : questions) {
            String questionText = question.getQuestionText();
            String answerText = question.getAnswerText();
            System.out.println(questionText + answerText);
        }
    }
}
