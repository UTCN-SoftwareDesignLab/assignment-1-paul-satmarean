package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import service.action.ActionService;
import service.user.AuthenticationService;

import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Created by Paul on 01/04/2018.
 */
public class ClientServiceImpl implements ClientService {

    private AuthenticationService authService;
    private ActionService actionService;
    private ClientRepository clientRepository;

    public ClientServiceImpl(AuthenticationService authService, ActionService actionService, ClientRepository clientRepository) {
        this.authService = authService;
        this.actionService = actionService;
        this.clientRepository = clientRepository;
    }

    @Override
    public Notification<List<Client>> findAll(){
        Notification<List<Client>> findNotification = new Notification<>();

        List<Client> clients = clientRepository.findAll();

        findNotification.setResult(clients);
        if(clients.size() == 0){
            findNotification.addError("There are no clients");

        }

        return findNotification;
    }

    @Override
    public Notification<Boolean> save(Client client) {
        Notification<Boolean> saveNotification = new Notification<>();
        saveNotification.setResult(clientRepository.save(client));
        return saveNotification;
    }

    @Override
    public Notification<Boolean> update(Client client) {
        Notification<Boolean> updateNotification = new Notification<>();
        updateNotification.setResult(clientRepository.update(client));
        return updateNotification;
    }

    @Override
    public Notification<Client> findByName(String name) {
        Notification<Client> findNotification = new Notification<>();
        try{
            Client client = clientRepository.findByName(name);
            findNotification.setResult(client);

        }catch(EntityNotFoundException e){
            findNotification.addError("Client not found.");

        }
        return findNotification;

    }

    @Override
    public Notification<Client> findByPnc(Long pnc) {
        Notification<Client> findNotification = new Notification<>();
        List<Client> clients = clientRepository.findAll();

        if(clients.size() == 0){
            findNotification.addError("Could not find any clients.");
            return findNotification;
        }

        for(Client client:clients){
            if(pnc == client.getPnc()){
                findNotification.setResult(client);
                return findNotification;
            }
        }
        findNotification.addError("Could not find client with given pnc.");
        return findNotification;
    }
}
