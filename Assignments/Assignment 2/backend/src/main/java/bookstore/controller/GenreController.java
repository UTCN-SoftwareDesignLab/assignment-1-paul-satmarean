package bookstore.controller;

import bookstore.entity.Genre;
import bookstore.service.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paul on 14/05/2018.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge =  3600)
@RequestMapping("/api")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;

    }


    @CrossOrigin
    @RequestMapping("/genres/")
    public ResponseEntity<List<Genre>> getGenres(){
        List<Genre> genres = this.genreService.findAll();

        if(genres == null || genres.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
    }

    //ADD
    @CrossOrigin
    @RequestMapping(value="/genres/", method = RequestMethod.POST)
    public ResponseEntity<?> addAuthor(@RequestBody Genre genre){
        if(genreService.findByName(genre.getName())!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        genreService.save(genre);
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);

    }


}
