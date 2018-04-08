package repository.action;

import model.Action;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class ActionRepositoryCacheDecorator extends ActionRepositoryDecorator {

    private Cache<Action> cache;

    public ActionRepositoryCacheDecorator(ActionRepository decoratedRepository, Cache<Action> cache) {
        super(decoratedRepository);
        this.cache = cache;
    }


    @Override
    public List<Action> findAll() {
        if(cache.hasResult()){
            return cache.load();
        }
        List<Action> actions = decoratedRepository.findAll();
        cache.save(actions);
        return actions;
    }

    @Override
    public List<Action> findByUserId(Long id) {
        return decoratedRepository.findByUserId(id);
    }

    @Override
    public Action findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Action action) {
        cache.invalidateCache();
        return decoratedRepository.save(action);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}
