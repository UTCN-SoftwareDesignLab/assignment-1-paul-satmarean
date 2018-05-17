package bookstore.service.genre;

import bookstore.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 01/05/2018.
 */
@Service
public interface GenreService {

    Genre findByName(String name);

    Genre save(Genre genre);

    List<Genre> findAll();

}
