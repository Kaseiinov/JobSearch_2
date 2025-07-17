package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exceptions.UserNotFoundException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> findAll(){
        List<User> users = userDao.findAll();
        return users
                .stream()
                .map(u -> UserDto
                        .builder()
                        .id(u.getId())
                        .name(u.getName())
                        .password(u.getPassword())
                        .build()
                ).toList();
    }

    @Override
    public UserDto findById(Long id){
        User user = userDao.findById(id).orElseThrow(UserNotFoundException::new);
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
