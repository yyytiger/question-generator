package com.yyytiger.question_generator.printer;

import com.yyytiger.question_generator.question.Question;

import java.util.List;

public interface QuestionsPrinter {
    void print(List<Question> questions);
}
