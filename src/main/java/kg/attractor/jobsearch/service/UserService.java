package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.model.User;

import java.util.List;

public interface UserService {
    void editUserById(UserDto userDto, Long id);

    List<UserDto> findAll();


    UserDto findByName(String name);

    UserDto findByEmail(String email);

    UserDto findByPhoneNumber(String number);

    List<UserDto> userBuilder(List<User> users);

    UserDto userBuilder(User user);
}
