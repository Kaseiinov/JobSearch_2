package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exceptions.UserNotFoundException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = userDtoBuilderToModel(userDto);
        userDao.saveUser(user);
        log.info("Saved user: {}", user.getEmail());
    }

    @Override
    public void editUserById(UserEditDto userDto, Long id){
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .phoneNumber(userDto.getPhoneNumber())
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

    public User userDtoBuilderToModel(UserDto userDto){
        return User
                .builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .accountType(userDto.getAccountType())
                .password(encoder.encode(userDto.getPassword()))
                .roleId(userDto.getRoleId())
                .enabled(userDto.getEnabled())
                .build();
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
