package com.yyytiger.question_generator.generator.impl;

import com.yyytiger.question_generator.generator.AbstractQuestionListGenerator;
import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.random.RandomHelper;

import java.util.List;

public class MixedTypesQuestionListGeneratorImpl extends AbstractQuestionListGenerator {
    private List<QuestionGenerator> questionGenerators;

    public MixedTypesQuestionListGeneratorImpl(int questionCount, List<QuestionGenerator> questionGenerators){
        super(questionCount);
        this.questionGenerators = questionGenerators;
    }

    @Override
    protected Question generateQuestion() {
        int index = RandomHelper.nextInt(0, this.questionGenerators.size() - 1);
        QuestionGenerator generator = this.questionGenerators.get(index);
        return generator.generateQuestion();
    }
}
