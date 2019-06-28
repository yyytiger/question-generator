package com.yyytiger.question_generator.printer.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.yyytiger.question_generator.printer.QuestionsPrinter;
import com.yyytiger.question_generator.question.Question;

import java.io.*;
import java.util.List;

public class PdfQuestionsPrinter implements QuestionsPrinter {
    private static final int Columns = 4;
    private String filePath;
    private BaseFont baseFont;

    public PdfQuestionsPrinter(String filePath) {
        this.filePath = filePath;
        try {
            this.baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //this.baseFont = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        }
        catch (DocumentException | IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void print(List<Question> questions) {
        try {
            Document document = new Document(PageSize.A4, 20F, 20F, 30F, 20F);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.filePath));
            document.open();

            //Draw a line
            drawALine(writer);

            //Create a table
            writeDocument(document, questions);

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void drawALine(PdfWriter writer){
        PdfContentByte cb = writer.getDirectContent();
        cb.setLineWidth(0.5f);	// Make a bit thicker than 1.0 default
        cb.setGrayStroke(0f); // 0 = black, 1 = white
        float x = 20f;
        float y = 790f;
        cb.moveTo(x, y);
        cb.lineTo(x + 550f, y);
        cb.stroke();
    }

    private void writeDocument(Document document, List<Question> questions) throws DocumentException {
//
//        Graphic g = new Graphic();
//        g.setHorizontalLine(1f, 98f);
//        document.add(g);

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
            addDataCell(table, text);
        }
        //Make up remaining cells to complete the table
        int remaining = Columns - questions.size() % Columns;
        for (int i=0; i<remaining; i++){
            addDataCell(table, "");
        }

        document.add(table);
    }

    private void addHeaderCell(PdfPTable table, String text){
        int fontSize = 14;
        int fontStyle = Font.BOLD;
        PdfPCell headerCell = new PdfPCell(createPhrase(text, fontSize, fontStyle));
        headerCell.setBorder(0);
        headerCell.setFixedHeight(30);
        table.addCell(headerCell);
    }

    private void addDataCell(PdfPTable table, String text){
        int fontSize = 10;
        PdfPCell headerCell = new PdfPCell(createPhrase(text, fontSize));
        headerCell.setBorder(0);
        headerCell.setFixedHeight(150);
        table.addCell(headerCell);
    }

    private Phrase createPhrase(String text, int fontSize){
        return new Phrase(text, new Font(this.baseFont, fontSize));
    }

    private Phrase createPhrase(String text, int fontSize, int fontStyle) {
        Phrase phrase = new Phrase(text, new Font(this.baseFont, fontSize, fontStyle));
        return phrase;
    }
}
