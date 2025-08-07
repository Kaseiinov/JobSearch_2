package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}")
    public void editById(@PathVariable Long id, @RequestBody @Valid UserEditDto userEditDto){
        userService.editUserById(userEditDto, id);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDto> searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {

        if (name != null) {
            return ResponseEntity.ok(userService.findByName(name));
        }
        if (email != null) {
            return ResponseEntity.ok(userService.findByEmail(email));
        }
        if (phone != null) {
            return ResponseEntity.ok(userService.findByPhoneNumber(phone));
        }

        return ResponseEntity.badRequest().build();
    }
}