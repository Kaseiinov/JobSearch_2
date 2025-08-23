package kg.attractor.jobsearch.service.impl;


import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.exceptions.UserNotFoundException;
import kg.attractor.jobsearch.model.Role;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repository.UserRepository;
import kg.attractor.jobsearch.service.RoleService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.CommonUtilities;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final FileUtil fileUtil;
    private final RoleService roleService;
    private final EmailService emailService;

    @Override
    public void saveUser(UserDto userDto) throws EmailAlreadyExistsException {
        User user = userDtoBuilderToModel(userDto);
        boolean isExists = userRepository.existsByEmail(userDto.getEmail());
        if (!isExists) {
            userRepository.save(user);
            log.info("Saved user: {}", user.getEmail());
        } else {
            throw new EmailAlreadyExistsException();
        }
    }

    @Override
    public void editUserByEmail(UserEditDto userDto, String email) {
        String filename = fileUtil.saveUploadFile(userDto.getUserImageDto().getFile(), "images/");

//        UserImage userImage = new UserImage();
//        userImage.setUser(findModelUserById(userDto.getId()));
//        userImage.setFileName(filename);

        User user = findModelUserByEmail(email);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(email);
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAvatar(filename);

        userRepository.save(user);
    }

//    @Override
//    public List<UserDto> findAll(){
//        List<User> users = userDao.findAll();
//        return userBuilder(users);
//    }
//
//    @Override
//    public UserDto findByName(String name){
//        User user = userRepository.findByName(name)
//        return userBuilder(user);
//    }

    @Override
    public UserEditDto findUserEditTypeByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserEditDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userBuilder(user);
    }

    @Override
    public User findModelUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

    }

    @Override
    public User findModelUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);

    }

    @Override
    public UserDto findByPhoneNumber(String number) {
        User user = userRepository.findByPhoneNumber(number).orElseThrow(UserNotFoundException::new);
        return userBuilder(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    @Override
    public void makeResetPasswdLink(HttpServletRequest request) throws UserNotFoundException, UnsupportedEncodingException, MessagingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLnk = CommonUtilities.getSiteURL(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLnk);
    }

    @Override
    public void updateResetPasswordToken(String token, String email){
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String password){
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }



    public User userDtoBuilderToModel(UserDto userDto) {
//        UserImage userImage = new UserImage();
//        userImage.setUser(findModelUserById(userDto.getId()));
//        userImage.setFileName(userDto.getAvatar());
        Role role = roleService.findRoleByName(userDto.getAccountType());

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
                .roles(List.of(role))
                .enabled(true)
                .build();
    }

//    @Override
//    public List<UserDto> userBuilder(List<User> users){
//
//
//        List<UserDto> userDtos = users
//                .stream()
//                .map(r -> UserDto.builder()
//                        .id(r.getId())
//                        .name(r.getName())
//                        .surname(r.getSurname())
//                        .age(r.getAge())
//                        .email(r.getEmail())
//                        .phoneNumber(r.getPhoneNumber())
//                        .avatar(r.getAvatar())
//                        .accountType(r.getAccountType())
//                        .password(r.getPassword())
//                        .build()).toList();
//        return userDtos;
//
//    }

    @Override
    public UserDto userBuilder(User user) {

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
