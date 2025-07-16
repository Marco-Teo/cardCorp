package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.dto.LogInDto;
import it.epicode.cardcorp.dto.UserDto;
import it.epicode.cardcorp.exeption.AlreadyExistException;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.exeption.ValidationException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.security.JwTool;
import it.epicode.cardcorp.service.AuthService;
import it.epicode.cardcorp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwTool jwTool;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody @Validated LogInDto loginDto,
            BindingResult bindingResult
    ) throws ValidationException, NotFoundException {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            throw new ValidationException(errors);
        }
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody @Validated UserDto userDto,
            BindingResult bindingResult
    ) throws ValidationException, AlreadyExistException {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            throw new ValidationException(errors);
        }
        User created = userService.registerUser(userDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/utente")
    public ResponseEntity<User> getUser(
            @RequestHeader("Authorization") String authHeader
    ) throws NotFoundException {
        String token = authHeader.replace("Bearer ", "");
        User u = jwTool.getUserFromToken(token);
        return ResponseEntity.ok(u);
    }
}
