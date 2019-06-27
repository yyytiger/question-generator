package com.yyytiger.question_generator.generator;

import com.yyytiger.question_generator.question.Question;

import java.util.List;

public interface QuestionListGenerator {
    List<Question> generateQuestions();
}
