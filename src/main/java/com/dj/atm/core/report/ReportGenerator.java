package com.dj.atm.core.report;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Date;

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


    public static byte[] generateReport()
            throws Exception {
        byte[] bytes = null;
        try {


            // Get the text that will be added to the PDF
            String text = "Input Parameter. Needs to replaced by a valid content ???";
            if (text == null || text.trim().length() == 0) {
                text = "You didn't enter any text.";
            }
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph(String.format(
                    "You have submitted the following text using the %s method:",
                    text)));
            document.add(new Paragraph(text));
            // step 5
            document.close();
            bytes = baos.toByteArray();
        } catch (DocumentException e) {
            throw new Exception(e.getMessage());
        }
        return bytes;
    }

}
