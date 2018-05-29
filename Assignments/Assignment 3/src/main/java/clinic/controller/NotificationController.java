package clinic.controller;

import clinic.entity.Notification;
import clinic.entity.User;
import clinic.service.notification.NotificationService;
import clinic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Paul on 21/05/2018.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
public class NotificationController {

    private UserService userService;

    private NotificationService notificationService;

    @Autowired
    public NotificationController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @RequestMapping("/notifications/")
    public ResponseEntity<?> getNotifications(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        List<Notification> notificationList = notificationService.findByUserId(user.getId());
        if(!notificationList.isEmpty()){
            notificationService.markAsRead(notificationList);
        }
        if(notificationList.isEmpty()) {
            return new ResponseEntity<List<Notification>>(notificationList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Notification>>(notificationList, HttpStatus.OK);
    }

}
