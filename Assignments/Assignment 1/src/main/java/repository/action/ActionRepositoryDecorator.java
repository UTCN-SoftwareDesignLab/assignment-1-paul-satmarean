package repository.action;

/**
 * Created by Paul on 01/04/2018.
 */
public abstract class ActionRepositoryDecorator implements ActionRepository{

    protected ActionRepository decoratedRepository;

    public ActionRepositoryDecorator(ActionRepository decoratedRepository) {
        this.decoratedRepository = decoratedRepository;
    }


}
