package clinic.service.notification;

import clinic.entity.Notification;
import clinic.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 21/05/2018.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> findByUserId(Long id) {
//        List<Notification> all = notificationRepository.findByUserId(id);
//        List<Notification> unread = new ArrayList<Notification>();
//        for(Notification n: all){
//            if(n.getIsRead()==false){
//                unread.add(n);
//            }
//        }
//        return unread;
////        return
        return notificationRepository.findByUserIdAndSeen(id, false);
    }


    @Override
    public void markAsRead(List<Notification> notifications) {
        for(Notification n: notifications){
            n.setSeen(true);
            notificationRepository.save(n);
        }
    }

    @Override
    public Notification add(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public void delete(List<Notification> notifications) {
        for(Notification n:notifications){
            notificationRepository.delete(n);
        }
    }
}
