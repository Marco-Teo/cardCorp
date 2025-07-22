package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.dto.CartaDto;
import it.epicode.cardcorp.dto.FilterDto;
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

    @PostMapping("/search")
    public ResponseEntity<List<Carta>> searchCarte(@RequestBody FilterDto filters) {
        return ResponseEntity.ok(cartaService.searchCarte(filters));
    }



    @GetMapping("/rarita")
    public ResponseEntity<List<Carta>> getByRarita(@RequestParam Rarita rarita) {
        return ResponseEntity.ok(cartaService.findByRarita(rarita));
    }

    @GetMapping("/prezzo")
    public ResponseEntity<List<Carta>> getByPrezzoRange(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(cartaService.findByPrezzoBetween(min, max));
    }

    @PostMapping("/{id}/preferita")
    public ResponseEntity<Void> markAsFavorite(@PathVariable int id) throws NotFoundException {
        cartaService.aggiungiAiPreferiti(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/preferita")
    public ResponseEntity<Void> unmarkAsFavorite(@PathVariable int id) throws NotFoundException {
        cartaService.rimuoviDaiPreferiti(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rarities")
    public Rarita[] getRarities() {
        return Rarita.values();
    }
}
