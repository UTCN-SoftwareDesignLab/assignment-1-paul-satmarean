package service.type;

import model.Type;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public interface TypeService {

    Notification<List<Type>> findAll();

    Notification<Type> findById(Long id);

}
