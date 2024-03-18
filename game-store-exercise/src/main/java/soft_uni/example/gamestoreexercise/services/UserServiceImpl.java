package soft_uni.example.gamestoreexercise.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soft_uni.example.gamestoreexercise.entities.DTOs.UserRegisterDTO;
import soft_uni.example.gamestoreexercise.entities.models.User;
import soft_uni.example.gamestoreexercise.repositories.UserRepository;

import java.util.Optional;

import static soft_uni.example.gamestoreexercise.constants.Exceptions.EMAIL_EXIST;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private User userToLog;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public String RegisterUser(String[] arguments) {
        String email = arguments[1];
        String password = arguments[2];
        String confirmPassword = arguments[3];
        String fullName = arguments[4];

        UserRegisterDTO userToRegister;

        try {
            userToRegister = new UserRegisterDTO(email,fullName,password,confirmPassword);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
        Optional<User> userToBeLogged = this.userRepository.findFirstByEmail(email);
        if (userToBeLogged.isPresent()){
            return EMAIL_EXIST;
        }
        User user = this.modelMapper.map(userToRegister, User.class);
        if (this.userRepository.count() == 0){
            user.setAdmin(true);
        }else {
            user.setAdmin(false);
        }
        this.userRepository.save(user);

        return userToRegister.successfulRegistered();
    }

    @Override
    public String LoginUser(String[] arguments) {
        return null;
    }




}
