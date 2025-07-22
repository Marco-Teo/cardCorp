package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.dto.OrdineDto;
import it.epicode.cardcorp.model.Ordine;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.repository.OrdineRepository;
import it.epicode.cardcorp.repository.UserRepository;
import it.epicode.cardcorp.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdineRepository ordineRepository;

    @PostMapping
    public ResponseEntity<Ordine> creaOrdine(@RequestBody OrdineDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User non trovato"));
        Ordine saved = ordineService.createOrdine(dto, user);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<Ordine> getAllOrdini(){
        return ordineService.getAllOrdini();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ordine>> getOrdiniByUser(@PathVariable int userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User non trovato"));
        List<Ordine> ordini = ordineRepository.findByUser(u);
        return ResponseEntity.ok(ordini);
    }
}
