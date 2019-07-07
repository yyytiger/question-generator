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
import com.yyytiger.question_generator.question.impl.integer.NoRemainderDivideQuestionImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class App 
{
    private static final String FolderPath = "D:\\questions\\";
    private static int DocumentCount = 10;

    public static void main( String[] args ) throws InterruptedException {
        for (int i = 0; i < DocumentCount; i++) {
            generateDocument();
            Thread.sleep(1000);
        }
    }

    private static void generateDocument(){
        //QuestionListGenerator generator = getMixedTypesGenerator(50);
        QuestionListGenerator generator = getWeighedTypesGenerator(50);
        List<Question> questions = generator.generateQuestions();

//        QuestionsPrinter printer = getPlainTextPrinter();
        QuestionsPrinter printer = getPdfPrinter();
        printer.print(questions);
    }

    private static QuestionListGenerator getWeighedTypesGenerator(int questionCount){
        List<QuestionGeneratorWeightDecorator> decorators = Arrays.asList(
                //Plus
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3),5),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3),5),
                //Minus
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3),5),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 3, 3),5),
                //Multi
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 3, 2), 10),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 2), 10),
                //Divide
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2),6),
                createDecorator(() -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2),6),
                createDecorator(() -> new NoRemainderDivideQuestionImpl(2, 3),4),
                createDecorator(() -> new NoRemainderDivideQuestionImpl(2, 2),4)
        );
        return new WeighedTypesQuestionListGeneratorImpl(questionCount, decorators);
    }

    private static QuestionListGenerator getMixedTypesGenerator(int questionCount){
        List<QuestionGenerator> questionGenerators = Arrays.asList(
                //Plus
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.PLUS, 3, 3),
                //Minus
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 4, 3),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MINUS, 3, 3),
                //Multi
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 3, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.MULTI, 2, 2),
                //Divide
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 3, 2),
                () -> new NDigitsOpMDigitsQuestionImpl(IntegerOperator.DIVIDE, 4, 2),
                () -> new NoRemainderDivideQuestionImpl(2, 3),
                () -> new NoRemainderDivideQuestionImpl(2, 2)
        );
        return new MixedTypesQuestionListGeneratorImpl(questionCount, questionGenerators);
    }

    private static QuestionGeneratorWeightDecorator createDecorator(QuestionGenerator questionGenerator, int weight){
        return new QuestionGeneratorWeightDecorator(questionGenerator, weight);
    }

    private static QuestionsPrinter getPlainTextPrinter(){
        String fileName = getFileName();
        String filePath = String.format(FolderPath + "%s.txt", fileName);
        return new PlainTextQuestionsPrinter(filePath);
    }

    private static QuestionsPrinter getPdfPrinter() {
        String fileName = getFileName();
        String questionFilePath = String.format(FolderPath + "%s_questions.pdf", fileName);
        String awswerFilePath = String.format(FolderPath + "%s_answers.pdf", fileName);
        return new PdfQuestionsPrinter(questionFilePath, awswerFilePath);
    }

    private static String getFileName(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
