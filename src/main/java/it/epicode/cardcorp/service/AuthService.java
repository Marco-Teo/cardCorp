package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.LogInDto;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.repository.UserRepository;
import it.epicode.cardcorp.security.JwTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwTool jwtTool;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public String login(LogInDto dto) throws NotFoundException {
        User u = userRepository.findOneByEmail(dto.getEmail())
                .orElseThrow(() -> new NotFoundException(
                        "Nessun utente trovato con questa email"));
        if (!passwordEncoder.matches(dto.getPassword(), u.getPassword())) {
            throw new NotFoundException("Password errata");
        }
        return jwtTool.createToken(u);
    }
}
