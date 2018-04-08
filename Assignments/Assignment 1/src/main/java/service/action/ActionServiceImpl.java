package service.action;

import model.*;
import model.builder.ActionBuilder;
import model.validation.Notification;
import repository.action.ActionRepository;
import service.account.AccountService;
import service.user.AuthenticationService;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class ActionServiceImpl implements ActionService {

    private ActionRepository actionRepository;


    public ActionServiceImpl(ActionRepository actionRepository, AuthenticationService authService) {
        this.actionRepository = actionRepository;
    }

    @Override
    public Notification<Boolean> addAction(Action action) {
        Notification<Boolean> addActionNotification  = new Notification<>();
        addActionNotification.setResult(actionRepository.save(action));
        return addActionNotification;
    }

    @Override
    public Notification<List<Action>> getUserActions(User user) {
        Notification<List<Action>> getActionNotification = new Notification<>();
        getActionNotification.setResult(actionRepository.findByUserId(user.getId()));
        return getActionNotification;
    }


}
