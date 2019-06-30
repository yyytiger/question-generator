package com.yyytiger.question_generator;

import com.yyytiger.question_generator.generator.QuestionGenerator;
import com.yyytiger.question_generator.generator.QuestionListGenerator;
import com.yyytiger.question_generator.generator.impl.MixedTypesQuestionListGeneratorImpl;
import com.yyytiger.question_generator.generator.impl.WeighedTypesQuestionListGeneratorImpl;
import com.yyytiger.question_generator.model.QuestionGeneratorWeightDecorator;
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
    private static final String FolderPath = "D:\\questions\\";
    public static void main( String[] args )
    {
        //QuestionListGenerator generator = getMixedTypesGenerator(50);
        QuestionListGenerator generator = getWeighedTypesGenerator(50);
        List<Question> questions = generator.generateQuestions();

//        QuestionsPrinter printer = getPlainTextPrinter();
        QuestionsPrinter printer = getPdfPrinter();
        printer.print(questions);
    }

    private static QuestionListGenerator getWeighedTypesGenerator(int questionCount){
        List<QuestionGeneratorWeightDecorator> decorators = Arrays.asList(
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 3, 2), 2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 2), 2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2),2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2),2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3),2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 3, 3),2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3),2),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3),2)
        );
        QuestionListGenerator generator = new WeighedTypesQuestionListGeneratorImpl(questionCount, decorators);
        return generator;
    }

    private static QuestionListGenerator getMixedTypesGenerator(int questionCount){
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
        QuestionListGenerator generator = new MixedTypesQuestionListGeneratorImpl(questionCount, questionGenerators);
        return generator;
    }

    private static QuestionGeneratorWeightDecorator createDecorator(QuestionGenerator questionGenerator, int weight){
        return new QuestionGeneratorWeightDecorator(questionGenerator, weight);
    }

    private static QuestionsPrinter getPlainTextPrinter(){
        String fileName = getFileName();
        String filePath = String.format(FolderPath + "%s.txt", fileName);
        QuestionsPrinter printer = new PlainTextQuestionsPrinter(filePath);
        return printer;
    }

    private static QuestionsPrinter getPdfPrinter() {
        String fileName = getFileName();
        String questionFilePath = String.format(FolderPath + "%s_questions.pdf", fileName);
        String awswerFilePath = String.format(FolderPath + "%s_answers.pdf", fileName);
        QuestionsPrinter printer = new PdfQuestionsPrinter(questionFilePath, awswerFilePath);
        return printer;
    }

    private static String getFileName(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
