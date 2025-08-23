package kg.attractor.jobsearch.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.exceptions.UserNotFoundException;
import kg.attractor.jobsearch.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(UserDto userDto) throws EmailAlreadyExistsException;

    void editUserByEmail(UserEditDto userDto, String email);

    UserEditDto findUserEditTypeByEmail(String email);

    UserDto findByEmail(String email);

    User findModelUserByEmail(String email);

    User findModelUserById(Long id);

    UserDto findByPhoneNumber(String number);

    UserDetails loadUserByUsername(String username) throws UserNotFoundException;

    void makeResetPasswdLink(HttpServletRequest request) throws UserNotFoundException, MessagingException, UnsupportedEncodingException, MessagingException;

    void updateResetPasswordToken(String token, String email);

    User getUserByResetPasswordToken(String token);

    void updatePassword(User user, String password);

    UserDto userBuilder(User user);
}
