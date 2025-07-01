package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.CartaDto;
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
        carta.setInCollezione(cartaDto.isInCollezione());
        carta.setPreferita(cartaDto.isPreferita());

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

    public List<Carta> getCartePreferite() {
        return cartaRepository.findByPreferitaTrue();
    }

    public List<Carta> searchCarteByName(String nome) {
        return cartaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Carta> findByRarita(Rarita rarita) {
        return cartaRepository.findByRarita(rarita);
    }

    public List<Carta> findByInCollezione(boolean inCollezione) {
        return cartaRepository.findByInCollezione(inCollezione);
    }

    public void deleteCartae(int id) throws NotFoundException {
        Carta carta = findById(id);
        cartaRepository.delete(carta);
    }
}
