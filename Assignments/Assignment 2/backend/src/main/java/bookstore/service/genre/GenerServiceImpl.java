package bookstore.service.genre;

import bookstore.entity.Genre;
import bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 01/05/2018.
 */
@Service
public class GenerServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenerServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findByName(String name) {
        return null;
    }
}
