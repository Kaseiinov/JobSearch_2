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
    public UserDto findByName(String name){
        User user = userDao.findByName(name).orElseThrow(UserNotFoundException::new);
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDto findByEmail(String email){
        User user = userDao.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDto findByPhoneNumber(String number){
        User user = userDao.findByPhoneNumber(number).orElseThrow(UserNotFoundException::new);
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .password(user.getPassword())
                .build();
    }
}
