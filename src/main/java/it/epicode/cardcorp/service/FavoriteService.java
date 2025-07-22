package it.epicode.cardcorp.service;

import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.repository.CartaRepository;
import it.epicode.cardcorp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired private UserRepository userRepo;
    @Autowired private CartaRepository cartaRepo;

    public List<Carta> getFavorites(int userId) throws NotFoundException {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User non trovato"));
        return cartaRepo.findByUsersWhoFavorited_Id(userId);
    }

    @Transactional
    public void addFavorite(int userId, int cartaId) throws NotFoundException {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User non trovato"));
        cartaRepo.findById(cartaId)
                .orElseThrow(() -> new NotFoundException("Carta non trovata"));
        userRepo.addFavorite(userId, cartaId);
    }

    @Transactional
    public void removeFavorite(int userId, int cartaId) throws NotFoundException {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User non trovato"));
        cartaRepo.findById(cartaId)
                .orElseThrow(() -> new NotFoundException("Carta non trovata"));
        userRepo.removeFavorite(userId, cartaId);
    }
}
