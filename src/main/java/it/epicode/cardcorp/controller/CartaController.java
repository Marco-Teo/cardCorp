package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.dto.CartaDto;
import it.epicode.cardcorp.enumeration.Rarita;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carte")
@CrossOrigin
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @GetMapping
    public ResponseEntity<List<Carta>> getAllCarte() {
        return ResponseEntity.ok(cartaService.getAllCarte());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carta> getCartaById(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(cartaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Carta> creaCarta(@RequestBody CartaDto cartaDto) {
        return ResponseEntity.ok(cartaService.saveCarta(cartaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable int id) throws NotFoundException {
        cartaService.deleteCartae(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Carta>> searchByNome(@RequestParam String nome) {
        return ResponseEntity.ok(cartaService.searchCarteByName(nome));
    }

    @GetMapping("/preferite")
    public ResponseEntity<List<Carta>> getPreferite() {
        return ResponseEntity.ok(cartaService.getCartePreferite());
    }

    @GetMapping("/rarita")
    public ResponseEntity<List<Carta>> getByRarita(@RequestParam Rarita rarita) {
        return ResponseEntity.ok(cartaService.findByRarita(rarita));
    }

    @GetMapping("/collezione")
    public ResponseEntity<List<Carta>> getByCollezione(@RequestParam boolean inCollezione) {
        return ResponseEntity.ok(cartaService.findByInCollezione(inCollezione));
    }

    @GetMapping("/prezzo")
    public ResponseEntity<List<Carta>> getByPrezzoRange(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(cartaService.findByPrezzoBetween(min, max));
    }
}
