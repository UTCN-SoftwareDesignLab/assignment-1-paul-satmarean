package bookstore.controller;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.repository.GenreRepository;
import bookstore.service.author.AuthorService;
import bookstore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paul on 01/05/2018.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private GenreRepository genreRepository;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreRepository genreRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreRepository = genreRepository;
    }

    //GET ALL
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMINISTRATOR')")
    @CrossOrigin
    @RequestMapping("/books/")
    public ResponseEntity<List<Book>> getBooks(){
        String context =  SecurityContextHolder.getContext().toString();
        List<Book> books = bookService.findAll();

        if(books.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    //GET ONE
    @CrossOrigin
    @RequestMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") Integer id){
        Book book = bookService.findOne(id);
        if(book==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //DELETE

    @CrossOrigin
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Integer id){
        if(bookService.findOne(id)==null){
            return new ResponseEntity("null",HttpStatus.NOT_FOUND);
        }
        bookService.delete(id);
        return new ResponseEntity("confirmed", HttpStatus.OK);
    }

    //ADD
    @CrossOrigin
    @RequestMapping(value="/books/", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody Book book){
        if(bookService.findByTitle(book.getTitle())!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        bookService.save(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //edit
    @CrossOrigin
    @RequestMapping(value="/books/", method = RequestMethod.PUT)
    public ResponseEntity<?> editBook(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

}
