package com.yyytiger.question_generator.printer.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.question.Question;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfQuestionsPrinter implements QuestionsPrinter {
    private static final int Columns = 4;
    private String questionFilePath;
    private String answerFilePath;
    private BaseFont baseFont;
    private String id;

    public PdfQuestionsPrinter(String questionFilePath, String answerFilePath) {
        this.questionFilePath = questionFilePath;
        this.answerFilePath = answerFilePath;
        this.id = new SimpleDateFormat("HHmmss").format(new Date());
        try {
            this.baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //this.baseFont = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void print(List<Question> questions) {
        printQuestions(questions);
        printAnswers(questions);
    }

    private void printQuestions(List<Question> questions) {
        try {
            Document document = new Document(PageSize.A4, 20F, 20F, 30F, 20F);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.questionFilePath));
            document.open();

            //Draw a line
            drawALine(writer);

            //Create a table
            writeQuestionDocument(document, questions);

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printAnswers(List<Question> questions) {
        try {
            Document document = new Document(PageSize.A4, 20F, 20F, 30F, 20F);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.answerFilePath));
            document.open();

            //Create a table
            writeAnswerDocument(document, questions);

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawALine(PdfWriter writer) {
        PdfContentByte cb = writer.getDirectContent();
        cb.setLineWidth(0.5f);    // Make a bit thicker than 1.0 default
        cb.setGrayStroke(0f); // 0 = black, 1 = white
        float x = 20f;
        float y = 780f;
        cb.moveTo(x, y);
        cb.lineTo(x + 550f, y);
        cb.stroke();
    }

    private void writeQuestionDocument(Document document, List<Question> questions) throws DocumentException {
        addId(document);

        PdfPTable table = new PdfPTable(Columns);
        table.setWidthPercentage(100);

        //Add header cells
        addHeaderCell(table, "日期：");
        addHeaderCell(table, "开始：");
        addHeaderCell(table, "结束：");
        addHeaderCell(table, "得分：");

        //Add data cells
        for (Question question : questions) {
            String text = question.getQuestionText();
            addDataCell(table, text, 150);
        }
        //Make up remaining cells to complete the table
        int remaining = Columns - questions.size() % Columns;
        for (int i = 0; i < remaining; i++) {
            addDataCell(table, "", 150);
        }

        document.add(table);
    }

    private void writeAnswerDocument(Document document, List<Question> questions) throws DocumentException {
        addId(document);

        PdfPTable table = new PdfPTable(Columns);
        table.setWidthPercentage(100);

        //Add data cells
        for (Question question : questions) {
            String text = question.getAnswerText();
            addDataCell(table, text, 30);
        }
        //Make up remaining cells to complete the table
        int remaining = Columns - questions.size() % Columns;
        for (int i = 0; i < remaining; i++) {
            addDataCell(table, "", 30);
        }

        document.add(table);
    }

    private void addId(Document document) throws DocumentException{
        document.add(new Paragraph(this.id, new Font(this.baseFont, 6, Font.NORMAL, BaseColor.GRAY)));
    }

    private void addHeaderCell(PdfPTable table, String text) {
        int fontSize = 14;
        int fontStyle = Font.BOLD;
        PdfPCell headerCell = new PdfPCell(createPhrase(text, fontSize, fontStyle));
        headerCell.setBorder(0);
        headerCell.setFixedHeight(30);
        table.addCell(headerCell);
    }

    private void addDataCell(PdfPTable table, String text, int height) {
        int fontSize = 10;
        PdfPCell headerCell = new PdfPCell(createPhrase(text, fontSize));
        headerCell.setBorder(0);
        headerCell.setFixedHeight(height);
        table.addCell(headerCell);
    }

    private Phrase createPhrase(String text, int fontSize) {
        return new Phrase(text, new Font(this.baseFont, fontSize));
    }

    private Phrase createPhrase(String text, int fontSize, int fontStyle) {
        return new Phrase(text, new Font(this.baseFont, fontSize, fontStyle));
    }
}
