package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Postazione;
import davidedangelo.U2_W2_D5.entities.Prenotazione;
import davidedangelo.U2_W2_D5.entities.Utente;
import davidedangelo.U2_W2_D5.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteService utenteService;

    public boolean prenota (String username, Long postazioneID, LocalDate data){
        if (isNullOrBlank(username)) throw new IllegalArgumentException("Username obbligatorio");
        idValido(postazioneID);
        if (data == null || data.isBefore(LocalDate.now())) throw new IllegalArgumentException("Data non valida");

        Utente utente = utenteService.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        Postazione postazione = postazioneService.findById(postazioneID)
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata"));

        if (prenotazioneRepository.existByUtenteAndData(utente, data))
            throw new IllegalStateException("Hai già una prenotazione per questa data");

        if (prenotazioneRepository.existByPostazioneAndData(postazione, data))
            throw new IllegalStateException("Postazione già prenotata in quella data");

        Prenotazione p = new Prenotazione(utente, postazione, data);
        prenotazioneRepository.save(p);
        return true;
    }

    public List<Prenotazione> findAllPrenotazioni(){
        return prenotazioneRepository.findAll();
    }

    private boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id non valido");
    }
}
