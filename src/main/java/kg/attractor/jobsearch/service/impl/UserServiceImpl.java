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
    public void editUserById(UserDto userDto, Long id){
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .accountType(userDto.getAccountType())
                .build();
        userDao.update(user, id);
    }

    @Override
    public List<UserDto> findAll(){
        List<User> users = userDao.findAll();
        return userBuilder(users);
    }

    @Override
    public UserDto findByName(String name){
        User user = userDao.findByName(name).orElseThrow(UserNotFoundException::new);
        return userBuilder(user);
    }

    @Override
    public UserDto findByEmail(String email){
        User user = userDao.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userBuilder(user);
    }

    @Override
    public UserDto findByPhoneNumber(String number){
        User user = userDao.findByPhoneNumber(number).orElseThrow(UserNotFoundException::new);
        return userBuilder(user);
    }

    @Override
    public List<UserDto> userBuilder(List<User> users){
        List<UserDto> userDtos = users
                .stream()
                .map(r -> UserDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .surname(r.getSurname())
                        .age(r.getAge())
                        .email(r.getEmail())
                        .phoneNumber(r.getPhoneNumber())
                        .avatar(r.getAvatar())
                        .accountType(r.getAccountType())
                        .password(r.getPassword())
                        .build()).toList();
        return userDtos;

    }

    @Override
    public UserDto userBuilder(User user){
        return UserDto.builder()
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
