package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto) throws EmailAlreadyExistsException;

    void editUserByEmail(UserEditDto userDto, String email);

//    List<UserDto> findAll();


    UserDto findByName(String name);

    UserEditDto findUserEditTypeByEmail(String email);

    UserDto findByEmail(String email);

    UserDto findByPhoneNumber(String number);

//    List<UserDto> userBuilder(List<User> users);

    UserDto userBuilder(User user);
}
