package repository.client;


/**
 * Created by Paul on 01/04/2018.
 */
public abstract class ClientRepositoryDecorator implements ClientRepository{

    protected ClientRepository decoratedRepository;

    public ClientRepositoryDecorator(ClientRepository clientRepository){
        this.decoratedRepository = clientRepository;
    }

}
