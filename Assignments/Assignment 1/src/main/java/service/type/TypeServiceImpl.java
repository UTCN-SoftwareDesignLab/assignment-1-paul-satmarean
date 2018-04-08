package service.type;

import model.Type;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.type.TypeRepository;

import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }



    @Override
    public Notification<List<Type>> findAll() {
        Notification<List<Type>> findNotification = new Notification<>();

        List<Type> types = typeRepository.findAll();
        if(types.size() == 0){
            findNotification.addError("No types found.");
        }
        findNotification.setResult(types);
        return findNotification;
    }

    @Override
    public Notification<Type> findById(Long id) {
        Notification<Type> findNotification = new Notification<>();
        try {
            Type type = typeRepository.findById(id);
            findNotification.setResult(type);
        }catch (EntityNotFoundException e){
            findNotification.addError("Could not find type.");
        }
        return findNotification;
    }
}
