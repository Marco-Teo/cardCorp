package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.CartaDto;
import it.epicode.cardcorp.dto.FilterDto;
import it.epicode.cardcorp.enumeration.Rarita;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    public Carta saveCarta(CartaDto cartaDto) {
        Carta carta = new Carta();
        carta.setDescrizione(cartaDto.getDescrizione());
        carta.setNome(cartaDto.getNome());
        carta.setPrezzo(cartaDto.getPrezzo());
        carta.setUrlImmagine(cartaDto.getUrlImmagine());
        carta.setRarita(cartaDto.getRarita());

        Carta savedCarta = cartaRepository.save(carta);
        return savedCarta;
    }

    public List<Carta> findByPrezzoBetween(int min, int max) {
        return cartaRepository.findByPrezzoBetween(min, max);
    }

    public List<Carta> getAllCarte() {
        return cartaRepository.findAll();
    }

    public Carta findById(int id) throws NotFoundException {
        Carta carta = cartaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carta con "+ id +" non trovato" ));
        return carta;
    }



    public List<Carta> searchCarte(FilterDto filters) {
        List<Carta> listaCarte;
        listaCarte = cartaRepository.findByNomeContainingIgnoreCaseAndRaritaAndPrezzoBetween(filters.getNome(), filters.getRarita(),
        filters.getMin(), filters.getMax());
        return listaCarte;
    }

    public List<Carta> findByRarita(Rarita rarita) {
        return cartaRepository.findByRarita(rarita);
    }



    public void aggiungiAiPreferiti(int id) throws NotFoundException {
        Carta carta = findById(id);
        cartaRepository.save(carta);
    }

    public void rimuoviDaiPreferiti(int id) throws NotFoundException {
        Carta carta = findById(id);
        cartaRepository.save(carta);
    }

    public void deleteCartae(int id) throws NotFoundException {
        Carta carta = findById(id);
        cartaRepository.delete(carta);
    }
}
