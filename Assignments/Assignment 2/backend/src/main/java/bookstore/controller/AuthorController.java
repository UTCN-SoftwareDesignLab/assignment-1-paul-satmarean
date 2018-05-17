package bookstore.controller;

import bookstore.entity.Author;
import bookstore.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paul on 23/04/2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
public class AuthorController {


    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //GET ALL
    @CrossOrigin
    @RequestMapping("/authors/")
    public ResponseEntity<List<Author>> getAuthors(){
        List<Author> authors = authorService.findAll();
        if(authors.isEmpty()){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    //GET ONE
    @CrossOrigin
    @RequestMapping("/authors/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable("id") Integer id){
        Author author = authorService.findOne(id);
        if(author!=null){
            return new ResponseEntity<Author>(author, HttpStatus.OK);
        }
        return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
    }

    //DELETE
    @RequestMapping(value="/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Integer id){
        if(authorService.findOne(id)==null){
            return new ResponseEntity("null", HttpStatus.NOT_FOUND);
        }
        authorService.delete(id);
        return new ResponseEntity("confirmed", HttpStatus.OK);
    }

    //ADD
    @CrossOrigin
    @RequestMapping(value="/authors/", method = RequestMethod.POST)
    public ResponseEntity<?> addAuthor(@RequestBody Author author){
        if(authorService.findByName(author.getName())!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.save(author);
        return new ResponseEntity<Author>(author, HttpStatus.OK);

    }




}
