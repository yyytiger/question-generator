package com.yyytiger.question_generator.generator;

import com.yyytiger.question_generator.question.Question;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQuestionListGenerator implements QuestionListGenerator {
    private int questionCount;

    public AbstractQuestionListGenerator(int questionCount) {
        this.questionCount = questionCount;
    }

    @Override
    public List<Question> generateQuestions(){

        List<Question> result = new ArrayList<>();
        for (int i = 0; i < getQuestionCount(); i++) {
            Question question = generateQuestion();
            result.add(question);
        }
        return result;
    }

    protected int getQuestionCount(){
        return this.questionCount;
    }

    protected abstract Question generateQuestion();
}
