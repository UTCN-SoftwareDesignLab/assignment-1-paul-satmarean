package clinic.repository;

import clinic.entity.Notification;
import com.sun.tools.corba.se.idl.constExpr.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Paul on 21/05/2018.
 */

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    void delete(List<Notification> notificationList);
    List<Notification> findByUserId(Long id);
    List<Notification> findByUserIdAndSeen(Long id, Boolean seen);
}
