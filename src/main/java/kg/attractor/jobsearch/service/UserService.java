package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();


    UserDto findByName(String name);

    UserDto findByEmail(String email);

    UserDto findByPhoneNumber(String number);
}
