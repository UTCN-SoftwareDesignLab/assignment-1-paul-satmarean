package clinic.entity.validator;

import clinic.entity.Consult;
import clinic.service.consult.ConsultService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 21/05/2018.
 */
public class ConsultValidator {
    private Consult consult;
    private ConsultService consultService;
    List<String> errors;

    public ConsultValidator(Consult consult, ConsultService consultService) {
        this.consult = consult;
        this.consultService = consultService;
        errors=new ArrayList<String>();
    }

    public ConsultValidator validate(){
        List<Consult> consults = this.consultService.findByUserId(consult.getUser().getId());

        for(Consult c: consults){
            long diff = this.consult.getDate().getTime()- c.getDate().getTime();
            if(diff<0){
                diff= -diff;
            }
            if((diff/(60*1000)) < 60){
                //e mai aproape de o ora de ea
                errors.add("Date invalid");
                return this;
            }
        }
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }
}
