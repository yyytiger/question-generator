package com.yyytiger.question_generator.generator.impl;

import com.yyytiger.question_generator.generator.AbstractQuestionListGenerator;
import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.model.QuestionGeneratorWeightDecorator;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.random.RandomHelper;

import java.util.List;

public class WeighedTypesQuestionListGeneratorImpl extends AbstractQuestionListGenerator {
    private List<QuestionGeneratorWeightDecorator> decorators;
    private int totalWeight;

    public WeighedTypesQuestionListGeneratorImpl(int questionCount, List<QuestionGeneratorWeightDecorator> decorators) {
        super(questionCount);
        this.decorators = decorators;
        this.totalWeight = decorators.stream()
                .map(QuestionGeneratorWeightDecorator::getWeight)
                .reduce(0, Integer::sum);
    }

    @Override
    protected Question generateQuestion() {
        int randomWeight = RandomHelper.nextInt(1, this.totalWeight);
        for (QuestionGeneratorWeightDecorator decorator : this.decorators) {
            randomWeight -= decorator.getWeight();
            if (randomWeight <= 0) {
                QuestionGenerator generator = decorator.getQuestionGenerator();
                return generator.generateQuestion();
            }
        }
        throw new RuntimeException("No suitable generator was found.");
    }
}
