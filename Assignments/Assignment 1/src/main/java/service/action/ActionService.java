package service.action;

import model.Action;
import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public interface ActionService {

    Notification<Boolean> addAction(Action action);

    Notification<List<Action>> getUserActions(User user);

//    void removeAction(Action action);

}
