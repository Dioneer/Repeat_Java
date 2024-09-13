package Pegas.validator;

import Pegas.DTO.CreateUserDTO;
import Pegas.entity.Gender;
import Pegas.entity.Role;
import Pegas.utils.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDTO>{
    private static volatile CreateUserValidator INSTANCE;
    private CreateUserValidator(){};
    public static CreateUserValidator getInstance(){
        if(INSTANCE==null){
            if(INSTANCE==null){
                return INSTANCE = new CreateUserValidator();
            }
        }
        return INSTANCE;
    }
    public ValidationResult isValid(CreateUserDTO createUserDTO){
        var result = new ValidationResult();
        if(!LocalDateFormatter.isValid(createUserDTO.getBirthday())){
            result.add(new Error("invalid.birthday", "Birthday is invalid"));
        }
        if(Gender.find(createUserDTO.getGender()).isEmpty()){
            result.add(new Error("invalid.gender", "Gender is invalid"));
        }
        if(Role.find(createUserDTO.getRole()).isEmpty()){
            result.add(new Error("invalid.role", "Role is invalid"));
        }
        return result;
    }
}
