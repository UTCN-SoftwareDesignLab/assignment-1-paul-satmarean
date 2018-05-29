package clinic.controller;

import clinic.entity.Client;
import clinic.entity.Consult;
import clinic.entity.Notification;
import clinic.entity.User;
import clinic.entity.validator.ClientValidator;
import clinic.entity.validator.ConsultValidator;
import clinic.service.consult.ConsultService;

import clinic.service.notification.NotificationService;
import clinic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
public class ConsultController {

    private ConsultService consultService;
    private UserService userService;
    private NotificationService notificationService;

    @Autowired
    public ConsultController(ConsultService consultService, UserService userService, NotificationService notificationService) {
        this.consultService = consultService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    //Get all
    @CrossOrigin
    @RequestMapping("/consults/")
    public ResponseEntity<List<Consult>> getConsults(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        if(user.getRole().getName().equals("SECRETARY")){
            List<Consult> consults = consultService.findAll();

            if(consults.isEmpty()){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<List<Consult>>(consults, HttpStatus.OK);
        }else{
            List<Consult> consults = consultService.findByUserId(user.getId());

            if(consults.isEmpty()){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<List<Consult>>(consults, HttpStatus.OK);
        }
    }

    //GET ONE BY ID
    @CrossOrigin
    @RequestMapping("/consults/{id}")
    public ResponseEntity<?> getConsult(@PathVariable("id") Long id){
        Consult consult = consultService.findById(id);
        if(consult!=null){
            return new ResponseEntity<Consult>(consult, HttpStatus.OK);
        }
        return new ResponseEntity<Consult>(HttpStatus.NOT_FOUND);
    }

    //GET ONE BY USER
    @CrossOrigin
    @RequestMapping("/consults/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable("id")Long id){
        List<Consult> consults = consultService.findByUserId(id);
        if(!consults.isEmpty()){
            return new ResponseEntity<List<Consult>>(consults, HttpStatus.OK);
        }
        return new ResponseEntity<Consult>(HttpStatus.NOT_FOUND);
    }

    //GET ONE BY CLIENT
    @CrossOrigin
    @RequestMapping("/consults/client/{id}")
    public ResponseEntity<?> findByClient(@PathVariable("id") Long id){
        List<Consult> consults = consultService.findByClientId(id);
        if(!consults.isEmpty()){
            return new ResponseEntity<List<Consult>>(consults, HttpStatus.OK);
        }
        return new ResponseEntity<Consult>(HttpStatus.NOT_FOUND);
    }

    //DELETE
    @CrossOrigin
    @RequestMapping(value="/consults/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteConsult(@PathVariable("id") Long id){



        if(consultService.findById(id)==null){
            return new ResponseEntity<String>("null", HttpStatus.NOT_FOUND);
        }
        consultService.delete(id);
        return new ResponseEntity<String>("confirmed", HttpStatus.OK);
    }

    //ADD
    @CrossOrigin
    @RequestMapping(value="/consults/", method=RequestMethod.POST)
    public ResponseEntity<?> addConsult(@RequestBody Consult consult){

        List<String> errors = new ConsultValidator(consult, this.consultService).validate().getErrors();
        if(!errors.isEmpty()){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

//        if(consultService.findById(consult.getId())!=null){
//            return new ResponseEntity(HttpStatus.CONFLICT);
//        }

        Notification notification = new Notification();
        notification.setUser(consult.getUser());


        Consult saved = consultService.save(consult);
        notification.setConsult(saved);
        notification.setMessage("New appointment");
        notification.setSeen(false);
        notificationService.add(notification);
        return new ResponseEntity<Consult>(saved, HttpStatus.OK);

    }

    //EDIT
    @CrossOrigin
    @RequestMapping(value="/consults/", method=RequestMethod.PUT)
    public ResponseEntity<?> editConsult(@RequestBody Consult consult){
        consultService.save(consult);
        return new ResponseEntity<Consult>(consult, HttpStatus.OK);
    }
}
