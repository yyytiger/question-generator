package com.yyytiger.question_generator.generator.impl;

import com.yyytiger.question_generator.generator.AbstractQuestionListGenerator;
import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.random.RandomHelper;

import java.util.ArrayList;
import java.util.List;

public class SingleTypeQuestionListGeneratorImpl extends AbstractQuestionListGenerator {
    private QuestionGenerator questionGenerator;

    public SingleTypeQuestionListGeneratorImpl(int questionCount, QuestionGenerator questionGenerator){
        super(questionCount);
        this.questionGenerator = questionGenerator;
    }

    @Override
    protected Question generateQuestion() {
        return this.questionGenerator.generateQuestion();
    }
}
