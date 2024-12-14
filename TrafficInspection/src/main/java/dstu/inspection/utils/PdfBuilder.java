package dstu.inspection.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class PdfBuilder {
    @GetMapping("/categories/pdf")
    public void createTablePdf(String[] headers, String[] tableHeaders,
                                      String[][] tableData, File file, Resource resource)
            throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        String ttfPath = resource.getFile().getAbsolutePath();
        BaseFont baseFont = BaseFont.createFont(ttfPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 14, Font.NORMAL);
        // Метаданные
        for (String header:
                headers) {
            document.add(new Paragraph(header, font));
        }
        // Дата создания файла
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formatDate = simpleDateFormat.format(new Date());
        document.add(new Paragraph(formatDate, font));
        document.add(new Paragraph("\n"));
        // Таблица с информацией
        PdfPTable table = new PdfPTable(tableHeaders.length);
        for (String header : tableHeaders) {
            table.addCell(new Paragraph(header, font));
        }
        for (String[] row : tableData) {
            for (String cell : row) {
                table.addCell(new Paragraph(cell, font));
            }
        }
        document.add(table);
        document.close();
    }
}
