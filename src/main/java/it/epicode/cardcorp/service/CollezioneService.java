package it.epicode.cardcorp.service;


import it.epicode.cardcorp.model.Collezione;
import it.epicode.cardcorp.repository.CartaCollezioneRepository;
import it.epicode.cardcorp.repository.CartaRepository;
import it.epicode.cardcorp.repository.CollezioneRepository;
import it.epicode.cardcorp.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollezioneService {

    @Autowired
    private CollezioneRepository collezioneRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private CartaCollezioneRepository cartaCollezioneRepository;

    public Collezione getCollezioneByUserId(int userId) throws NotFoundException {
        return collezioneRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Collezione non trovata per utente con id: " + userId));
    }
}
