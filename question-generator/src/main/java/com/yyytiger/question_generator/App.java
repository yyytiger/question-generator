package com.yyytiger.question_generator;

import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.generator.impl.MixedTypesQuestionListGeneratorImpl;
import com.yyytiger.question_generator.operator.IntegerOperator;
import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.printer.impl.PdfQuestionsPrinter;
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
        int questionCount = 50;
        List<QuestionGenerator> questionGenerators = Arrays.asList(
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 3, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 3, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3)
        );

        MixedTypesQuestionListGeneratorImpl generator = new MixedTypesQuestionListGeneratorImpl(questionCount, questionGenerators);
        List<Question> questions = generator.generateQuestions();

//        QuestionsPrinter printer = getPlainTextPrinter();
        QuestionsPrinter printer = getPdfPrinter();
        printer.print(questions);
    }

    private static QuestionsPrinter getPlainTextPrinter(){
        String filePath = String.format("D:\\questions\\questions_%s.txt", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        QuestionsPrinter printer = new PlainTextQuestionsPrinter(filePath);
        return printer;
    }

    private static QuestionsPrinter getPdfPrinter() {
        String filePath = String.format("D:\\questions\\questions_%s.pdf", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        QuestionsPrinter printer = new PdfQuestionsPrinter(filePath);
        return printer;
    }
}
