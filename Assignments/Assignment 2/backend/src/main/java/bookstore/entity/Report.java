package bookstore.entity;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 14/05/2018.
 */
public class Report {

    List<Book> books;

    public Report(List<Book> books) {
        this.books = books;
    }

    public InputStream getStream(){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = new Document();

        try{
            PdfWriter.getInstance(document, stream);
            document.open();

            document.add(this.generateTable());
            Date date = new Date(System.currentTimeMillis());
            document.add(new Paragraph("Generated at "+date.toString()));

            document.close();
        }catch(DocumentException e){
            System.out.println(e.getMessage());
        }
        return new ByteArrayInputStream(stream.toByteArray());
    }


    private PdfPTable generateTable(){
        PdfPTable table = new PdfPTable(5);

        table.setSpacingBefore(15);
        table.addCell("Title");
        table.addCell("Price");
        table.addCell("Quantity");
        table.addCell("Author");
        table.addCell("Genre");

        for(Book book:this.books){
            table.addCell(String.valueOf(book.getTitle()));
            table.addCell(String.valueOf(book.getPrice()));
            table.addCell(String.valueOf(book.getQuantity()));
            table.addCell(String.valueOf(book.getAuthor().getName()));
            table.addCell(String.valueOf(book.getGenre().getName()));

        }

        return table;
    }
}
