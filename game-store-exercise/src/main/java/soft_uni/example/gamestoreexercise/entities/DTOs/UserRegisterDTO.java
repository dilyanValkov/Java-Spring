package soft_uni.example.gamestoreexercise.entities.DTOs;

import lombok.*;
import java.util.regex.Pattern;

import static soft_uni.example.gamestoreexercise.constants.Exceptions.*;
import static soft_uni.example.gamestoreexercise.constants.Validations.EMAIL_PATTERN;
import static soft_uni.example.gamestoreexercise.constants.Validations.PASSWORD_PATTERN;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class UserRegisterDTO {

    private String email;

    private String fullName;

    private String password;

    private String confirmPassword;

    public UserRegisterDTO(String email, String fullName, String password, String confirmPassword) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        validate();
    }
    private void validate(){
        if (!Pattern.matches(EMAIL_PATTERN, this.email)){
            throw new IllegalArgumentException(INVALID_EMAIL);
        }

        if (!Pattern.matches(PASSWORD_PATTERN,this.password)){
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }
        if (!this.password.equals(confirmPassword)){
            throw new IllegalArgumentException(INVALID_CONFIRMED_PASSWORD);
        }
    }
    public String successfulRegistered(){
        return this.fullName + " was registered.";
    }
}
