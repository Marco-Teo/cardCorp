package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.UserDto;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email già registrata. Prova a fare login o usa un'altra email.");
        }

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIndirizzo(userDto.getIndirizzo());

        try {
            User savedUser = userRepository.save(user);
            emailService.inviaBenvenuto(savedUser);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Errore di integrità: email duplicata o dati non validi.");
        }
    }

    public User findById(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User con id " + id+ " trovato"));
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User updateUser(int id, UserDto userDto) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User con id: " + id + " non trovato"));

        if (!user.getEmail().equals(userDto.getEmail()) && userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Questa email è già registrata da un altro utente.");
        }

        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIndirizzo(userDto.getIndirizzo());

        User updatedUser = userRepository.save(user);

        emailService.modificaUtente(updatedUser);

        return updatedUser;
    }

    public void deleteUser(int id) throws NotFoundException {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User con id: " + id + " non trovato"));

        userRepository.delete(userToDelete);

        emailService.utenteEliminato(userToDelete);

        System.out.println("Utente con id " + id + " eliminato e email inviata.");
    }
}
