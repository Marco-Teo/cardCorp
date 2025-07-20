package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.dto.UserDto;
import it.epicode.cardcorp.exeption.AlreadyExistException;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws NotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username")
    public ResponseEntity<Optional<User>> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> emailExists(@RequestParam String email) {
        boolean exists = userService.emailExists(email);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserDto userDto) throws NotFoundException, AlreadyExistException {
        User updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok("Utente eliminato correttamente");
    }
}
