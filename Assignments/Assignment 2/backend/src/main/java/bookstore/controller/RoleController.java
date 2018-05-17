package bookstore.controller;

import bookstore.entity.Author;
import bookstore.entity.Role;
import bookstore.repository.GenreRepository;
import bookstore.service.author.AuthorService;
import bookstore.service.book.BookService;
import bookstore.service.role.RoleService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Paul on 14/05/2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge =  3600)
@RequestMapping("/api")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @CrossOrigin
    @RequestMapping("/roles/")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = this.roleService.getRoles();

        if(roles == null || roles.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }


}
