package bookstore.controller;

import bookstore.security.AuthResponse;
import bookstore.security.Credentials;
import bookstore.service.security.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 01/05/2018.
 */

@RestController
@RequestMapping("/api")
public class LoginController  {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @RequestMapping(value= "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Credentials credentials){
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        if(username == null || password == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        String token = this.authenticationService.login(credentials);

        return new ResponseEntity(new AuthResponse(token), HttpStatus.OK);
    }


}
