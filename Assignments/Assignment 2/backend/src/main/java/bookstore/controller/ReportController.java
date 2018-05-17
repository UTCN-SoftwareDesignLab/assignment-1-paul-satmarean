package bookstore.controller;

import bookstore.entity.Report;
import bookstore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 14/05/2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
public class ReportController {

    private BookService bookService;

    @Autowired
    public ReportController(BookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin
    @RequestMapping("/report")
    public ResponseEntity<?> getReport(){
        Report report = new Report(bookService.findAll());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(report.getStream()));
    }

}
