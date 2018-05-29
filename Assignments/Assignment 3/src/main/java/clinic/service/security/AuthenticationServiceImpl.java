package clinic.service.security;

import clinic.security.Credentials;
import clinic.security.JwtUtils;
import clinic.service.security.AuthenticationService;
import clinic.service.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 02/05/2018.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public String login(Credentials credentials) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(credentials.getUsername());

//        if(userDetails==null){
//            return null;
//        }
//
//        if(credentials.getPassword()!= userDetails.getPassword()){
//            return null;
//        }
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.jwtUtils.generateToken(userDetails);

        return token;
    }
}
