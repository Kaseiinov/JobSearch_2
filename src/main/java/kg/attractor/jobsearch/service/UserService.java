package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);
}
