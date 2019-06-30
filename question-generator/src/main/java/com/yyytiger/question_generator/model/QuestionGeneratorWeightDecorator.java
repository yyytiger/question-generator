package com.yyytiger.question_generator.model;

import com.yyytiger.question_generator.generator.QuestionGenerator;

public class QuestionGeneratorWeightDecorator {
    private QuestionGenerator questionGenerator;
    private int weight;

    public QuestionGeneratorWeightDecorator(QuestionGenerator questionGenerator, int weight){
        this.questionGenerator = questionGenerator;
        this.weight = weight;
    }

    public QuestionGenerator getQuestionGenerator() {
        return questionGenerator;
    }

    public void setQuestionGenerator(QuestionGenerator questionGenerator) {
        this.questionGenerator = questionGenerator;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
