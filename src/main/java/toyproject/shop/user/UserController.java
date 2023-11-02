package toyproject.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto){
        Long userId = userService.signUp(userDto);

        URI uri = fromPath("/users/{userId}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signIn(userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("사용자 삭제");
    }

}
