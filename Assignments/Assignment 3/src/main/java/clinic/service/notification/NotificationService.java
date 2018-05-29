package clinic.service.notification;

import clinic.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 21/05/2018.
 */
@Service
public interface NotificationService {

    List<Notification> findByUserId(Long id);

    void markAsRead(List<Notification> notifications);


    Notification add(Notification notification);

    void delete(Notification notification);

    void delete(List<Notification> notifications);

}
