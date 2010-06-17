package com.dj.atm.core.report;

import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.service.DeveloperService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: ScriptRunner
 * Date: May 8, 2010
 * Time: 5:16:39 PM
 */
public class ReportGenerator {


    private String reportHeader;
    private String reportFooter;
    private String auther;
    private Date date;


    public byte[] generateReport(DeveloperService developerService, Developer developer)
            throws Exception {
        byte[] bytes = null;


        try {

            // Get the text that will be added to the PDF
            String text = "\u0d39\u0d32\u0d4b \u0d07\u0d35\u0d3f\u0d1f\u0d46" + " Deepak Jacob " + "\u0d0e\u0d32\u0d4d\u0d32\u0d3e\u0d35\u0d30\u0d4d\u0d15\u0d4d\u0d15\u0d41\u0d02 \u0d38\u0d41\u0d16\u0d2e\u0d3e\u0d23\u0d4d";
            text = developer.getName().getFirstName();
            //text = "Deepak Jacob";

            //bytes[]  ba = "".getBytes()
            //String text = new String(bytes, "UTF-8");
            if (text == null || text.trim().length() == 0) {
                text = "You didn't enter any text.";
            }
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4:
            //String text = "\u0936\u093e\u0902\u0924\u093f";
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/arialuni.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            document.add(new Paragraph("Pure iText: " + text,
                    new com.lowagie.text.Font(bf, 12)));
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(100, 50);
            Graphics2D g2 = tp.createGraphicsShapes(100, 50);
            java.awt.Font font = new java.awt.Font("Arial Unicode MS",
                    java.awt.Font.PLAIN, 12);
            g2.setFont(font);
            g2.drawString("Graphics2D: " + text, 0, 40);
            g2.dispose();
            cb.addTemplate(tp, 36, 750);

            // step 5
            document.close();

            bytes = baos.toByteArray();
        } catch (DocumentException e) {
            throw new Exception(e.getMessage());
        }
        return bytes;
    }

    public byte[] generateReport(List<Developer> developers) throws IOException, DocumentException {


        byte[] bytes = null;

        // Get the text that will be added to the PDF
        String text = "\u0d39\u0d32\u0d4b \u0d07\u0d35\u0d3f\u0d1f\u0d46" + " Deepak Jacob " + "\u0d0e\u0d32\u0d4d\u0d32\u0d3e\u0d35\u0d30\u0d4d\u0d15\u0d4d\u0d15\u0d41\u0d02 \u0d38\u0d41\u0d16\u0d2e\u0d3e\u0d23\u0d4d";
        //text = "Deepak Jacob";

        //bytes[]  ba = "".getBytes()
        //String text = new String(bytes, "UTF-8");
        if (text == null || text.trim().length() == 0) {
            text = "You didn't enter any text.";
        }
        // step 1
        Document document = new Document();
        // step 2
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4:
        //String text = "\u0936\u093e\u0902\u0924\u093f";
        BaseFont bf = BaseFont.createFont("c:/windows/fonts/arialuni.ttf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        /*document.add(new Paragraph("Pure iText: " + text,
                new com.lowagie.text.Font(bf, 12)));*/

        PdfContentByte cb = writer.getDirectContent();
        PdfTemplate template = cb.createTemplate(500, 500);
        cb.addTemplate(template, 100, 400);


        Graphics2D g2 = template.createGraphicsShapes(600, 100);


        java.awt.Font font = new java.awt.Font("Arial Unicode MS",
                java.awt.Font.PLAIN, 12);
        g2.setFont(font);


        g2.drawString("Deepak JAcob", 12, 13);


        g2.dispose();
        cb.addTemplate(template, 36, 750);

        // step 5
        document.close();

        bytes = baos.toByteArray();
        return bytes;
    }

    

}
