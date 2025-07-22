// src/main/java/it/epicode/cardcorp/service/OrdineService.java
package it.epicode.cardcorp.service;

import it.epicode.cardcorp.dto.OrdineCartaDto;
import it.epicode.cardcorp.dto.OrdineDto;
import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.model.Ordine;
import it.epicode.cardcorp.model.OrdineCarta;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.repository.CartaRepository;
import it.epicode.cardcorp.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private CartaRepository cartaRepository;

    public Ordine createOrdine(OrdineDto dto, User user) {
        Ordine ordine = new Ordine();
        ordine.setDataOrdine(dto.getDataOrdine());
        ordine.setIndirizzo(dto.getIndirizzo());
        ordine.setUser(user);

        List<OrdineCarta> dettagli = dto.getCarte().stream()
                .map(item -> {
                    Carta carta = cartaRepository.findById(item.getCartaId())
                            .orElseThrow(() -> new IllegalArgumentException("Carta non trovata: " + item.getCartaId()));
                    OrdineCarta oc = new OrdineCarta();
                    oc.setOrdine(ordine);
                    oc.setCarta(carta);
                    oc.setQuantita(item.getQuantita());
                    return oc;
                })
                .collect(Collectors.toList());

        ordine.setCarteOrdinate(dettagli);
        return ordineRepository.save(ordine);
    }

    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }
}
