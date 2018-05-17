package bookstore.service.author;

import bookstore.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 23/04/2018.
 */
@Service
public interface AuthorService {

    List<Author> findAll();

    Author findOne(Integer id);

    Author findByName(String name);

    void save(Author author);

    void delete(Integer id);
}
