package com.yyytiger.question_generator.printer.impl;

import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.question.Question;

import java.util.List;

public class ConsoleQuestionsPrinter implements QuestionsPrinter {
    private static final int LineWidth = 80;
    private static final int PageHeight = 51;
    private static final int RowLines = 10;
    private int columns;

    public ConsoleQuestionsPrinter(int columns){
        this.columns = columns;
    }

    @Override
    public void print(List<Question> questions) {
        int columnIndex = 0;
        int lineIndex = 0;
        int columnWidth = LineWidth / columns;
        for (Question question : questions) {
            String text = question.getQuestionText();
            System.out.print(text);
            printSpaces(columnWidth - text.length());
            columnIndex++;

            if (columnIndex == columns) {
                columnIndex = 0;
                printLines(RowLines);
                lineIndex += RowLines;
                if (PageHeight - lineIndex < RowLines) {
                    printLines(PageHeight - lineIndex);
                    lineIndex = 0;
                }
            }
        }
    }

    private void printSpaces(int count){
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private void printLines(int count){
        for (int i = 0; i < count; i++)
            System.out.println();
    }
}
