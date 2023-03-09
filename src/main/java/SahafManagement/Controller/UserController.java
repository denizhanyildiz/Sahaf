package SahafManagement.Controller;

import SahafManagement.Entity.User;
import SahafManagement.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id +" id numaralı kullanıcı veri tabanından silindi..");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
}
