package it.epicode.cardcorp.controller;

import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/favorites")
public class FavoriteController {

    @Autowired private FavoriteService favService;

    @GetMapping
    public ResponseEntity<List<Carta>> listFavs(@PathVariable int userId)
            throws NotFoundException {
        List<Carta> favs = favService.getFavorites(userId);
        if (favs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favs);
    }

    @PostMapping("/{cartaId}")
    public ResponseEntity<Void> addFav(
            @PathVariable int userId,
            @PathVariable int cartaId
    ) throws NotFoundException {
        favService.addFavorite(userId, cartaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartaId}")
    public ResponseEntity<Void> removeFav(
            @PathVariable int userId,
            @PathVariable int cartaId
    ) throws NotFoundException {
        favService.removeFavorite(userId, cartaId);
        return ResponseEntity.ok().build();
    }
}
