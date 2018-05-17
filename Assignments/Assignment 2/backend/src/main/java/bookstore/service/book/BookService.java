package bookstore.service.book;

import bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 01/05/2018.
 */
@Service
public interface BookService {

    List<Book> findAll();

    Book findOne(Integer id);

    Book findByTitle(String title);

    void delete(Integer id);

    Book update(Book book);

    void save(Book book);
}
