package com.yyytiger.question_generator;

import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.generator.impl.MixedTypesQuestionListGeneratorImpl;
import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.printer.impl.PlainTextQuestionsPrinter;
import com.yyytiger.question_generator.question.Question;
import com.yyytiger.question_generator.question.impl.integer.NDigitsOpMDigitsQuestionImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<QuestionGenerator> questionGenerators = Arrays.asList(
                new QuestionGenerator() {
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 3);
                    }
                },
                new QuestionGenerator(){
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2);
                    }
                },
                new QuestionGenerator(){
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2);
                    }
                },
                new QuestionGenerator(){
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3);
                    }
                },
                new QuestionGenerator(){
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3);
                    }
                },
                new QuestionGenerator(){
                    @Override
                    public Question generateQuestion() {
                        return new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3);
                    }
                }
        );

        MixedTypesQuestionListGeneratorImpl generator = new MixedTypesQuestionListGeneratorImpl(100, questionGenerators);
        List<Question> questions = generator.generateQuestions();

        String filePath = String.format("D:\\questions\\questions_%s.txt", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        QuestionsPrinter printer = new PlainTextQuestionsPrinter(filePath, 4);
        printer.print(questions);
    }
}
