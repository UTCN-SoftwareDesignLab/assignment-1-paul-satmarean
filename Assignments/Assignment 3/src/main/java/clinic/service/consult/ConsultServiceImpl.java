package clinic.service.consult;


import clinic.entity.Client;
import clinic.entity.Consult;
import clinic.entity.User;
import clinic.repository.ClientRepository;
import clinic.repository.ConsultRepository;
import clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@Service
public class ConsultServiceImpl implements ConsultService{

    private ConsultRepository consultRepository;
    private UserRepository userRepository;
    private ClientRepository clientRepository;

    @Autowired
    public ConsultServiceImpl(ConsultRepository consultRepository, UserRepository userRepository, ClientRepository clientRepository) {
        this.consultRepository = consultRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Consult> findAll() {
        return consultRepository.findAll();
    }

    @Override
    public List<Consult> findByUserId(Long id) {
        User user = userRepository.findOne(id);
        if(user!=null){
            return consultRepository.findByUser(user);
        }
        return null;
    }

    @Override
    public List<Consult> findByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        if(client!=null){
            return consultRepository.findByClient(client);
        }
        return null;
    }

    @Override
    public Consult findById(Long id) {
        return consultRepository.findOne(id);
    }

    @Override
    public List<Consult> findByUser(User user) {
        return consultRepository.findByUser(user);
    }

    @Override
    public List<Consult> findByClient(Client client) {
        return consultRepository.findByClient(client);
    }

    @Override
    public Consult save(Consult consult) {
        return consultRepository.save(consult);
    }

    @Override
    public void delete(Consult consult) {
        consultRepository.delete(consult);
    }

    @Override
    public void delete(Long id) {
        consultRepository.delete(id);
    }
}
