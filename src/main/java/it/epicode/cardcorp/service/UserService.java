package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.UserDto;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIndirizzo(userDto.getIndirizzo());

        User savedUser = userRepository.save(user);

        emailService.inviaBenvenuto(savedUser);
        return savedUser;
    }

    public User findById(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con id " + id));
        return user;
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
