package com.yyytiger.question_generator.printer.impl;

import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.question.Question;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

public class PlainTextQuestionsPrinter implements QuestionsPrinter {
    private static final int Columns = 4;
    private static final int LineWidth = 80;
    private static final int PageHeight = 51;
    private static final int RowLines = 10;
    private String filePath;
    private String lineSeparator = System.getProperty("line.separator");

    public PlainTextQuestionsPrinter(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void print(List<Question> questions) {
        StringBuffer buffer = new StringBuffer();

        int columnIndex = 0;
        int lineIndex = 0;
        int columnWidth = LineWidth / Columns;
        for (Question question : questions) {
            String text = question.getQuestionText();
            buffer.append(text);
            //System.out.print(text);
            printSpaces(buffer, columnWidth - text.length());
            columnIndex++;

            if (columnIndex == Columns) {
                columnIndex = 0;
                printLines(buffer, RowLines);
                lineIndex += RowLines;
                if (PageHeight - lineIndex < RowLines) {
                    printLines(buffer, PageHeight - lineIndex);
                    lineIndex = 0;
                }
            }
        }

        createFile(buffer.toString());
    }

    private void printSpaces(StringBuffer buffer, int count){
        for (int i = 0; i < count; i++) {
            buffer.append(" ");
            //System.out.print(" ");
        }
    }

    private void printLines(StringBuffer buffer, int count){
        for (int i = 0; i < count; i++) {
            buffer.append(lineSeparator);
            //System.out.println();
        }
    }

    private void createFile(String data) {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            // write
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            bw.close();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
