package clinic.controller;

import clinic.entity.Client;
import clinic.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge =  3600)
@RequestMapping("/api")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //GET ALL
    @CrossOrigin
    @RequestMapping("/clients/")
    public ResponseEntity<List<Client>> getAuthors(){
        List<Client> clients = clientService.findAll();
        if(clients.isEmpty()){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    //GET ONE
    @CrossOrigin
    @RequestMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") Long id){
        Client client = clientService.findById(id);
        if(client!=null){
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        }
        return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
    }

    //DELETE
    @RequestMapping(value="/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        if(clientService.findById(id)==null){
            return new ResponseEntity<String>("null", HttpStatus.NOT_FOUND);
        }
        clientService.delete(id);
        return new ResponseEntity<String>("confirmed", HttpStatus.OK);
    }

    //ADD
    @CrossOrigin
    @RequestMapping(value="/clients/", method = RequestMethod.POST)
    public ResponseEntity<?> addClient(@RequestBody Client client){
        if(clientService.findByName(client.getName())!=null){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        clientService.save(client);
        return new ResponseEntity<Client>(client, HttpStatus.OK);

    }



}
