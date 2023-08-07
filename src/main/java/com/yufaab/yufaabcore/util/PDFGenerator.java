package com.yufaab.yufaabcore.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Setter
public class PDFGenerator {

  private List<Counselling2022> counselling2022List;

  private static final Font COURIER = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);

  public byte[] generatePdf() throws DocumentException, IOException {
    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter.getInstance(document, baos);

    document.open();
    addDocTitle(document);
    createTable(document);
    document.close();

    return baos.toByteArray();
  }

  private void addDocTitle(Document document) throws DocumentException {
    Paragraph p1 = new Paragraph();
    leaveEmptyLine(p1, 1);
    p1.add(new Paragraph("Counselling Report", COURIER));
    p1.setAlignment(Element.ALIGN_CENTER);
    leaveEmptyLine(p1, 1);
    document.add(p1);
  }

  private void leaveEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

  private void createTable(Document document) throws DocumentException {
    Paragraph paragraph = new Paragraph();
    leaveEmptyLine(paragraph, 3);
    document.add(paragraph);

    List<String> columnNames = List.of(new String[]{
            "Sr No.", "Seat Type", "Academic Programme", "Institute", "Opening Rank", "Closing Rank"
    });
    PdfPTable table = new PdfPTable(6);

    for(int i=0; i<6; i++) {
      PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(BaseColor.CYAN);
      table.addCell(cell);
    }

    table.setHeaderRows(1);
    setDbData(table);
    document.add(table);
  }

  private void setDbData(PdfPTable table) {

    int index=1;
    for (Counselling2022 data : counselling2022List) {

      table.setWidthPercentage(100);
      table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

      table.addCell(String.valueOf(index));
      table.addCell(data.getSeatType());
      table.addCell(data.getAcademicProgramName());
      table.addCell(data.getInstitute());
      table.addCell(String.valueOf(data.getOpeningRank()));
      table.addCell(String.valueOf(data.getClosingRank()));
      index++;
    }

  }
}
