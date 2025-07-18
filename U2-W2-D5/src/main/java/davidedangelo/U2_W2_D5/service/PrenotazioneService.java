package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Postazione;
import davidedangelo.U2_W2_D5.entities.Prenotazione;
import davidedangelo.U2_W2_D5.entities.Utente;
import davidedangelo.U2_W2_D5.exception.PrenotazioneException;
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
        try {
            if (isNullOrBlank(username)) throw new PrenotazioneException("Username obbligatorio");
            idValido(postazioneID);
            if (data == null || data.isBefore(LocalDate.now())) throw new PrenotazioneException("Data non valida");

            Utente utente = utenteService.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new PrenotazioneException("Utente non trovato"));
            Postazione postazione = postazioneService.findById(postazioneID)
                    .orElseThrow(() -> new PrenotazioneException("Postazione non trovata"));

            if (prenotazioneRepository.existsByUtenteAndDataPrenotazione(utente, data))
                throw new PrenotazioneException("Hai già una prenotazione per questa data");

            if (prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione, data))
                throw new PrenotazioneException("Postazione già prenotata in quella data");

            Prenotazione p = new Prenotazione(utente, postazione, data);
            prenotazioneRepository.save(p);
            return true;
        }catch (PrenotazioneException e){
            throw e;
        }catch (Exception e){
            throw new PrenotazioneException("errore, riprova");
        }
    }

    public List<Prenotazione> findAllPrenotazioni(){
        return prenotazioneRepository.findAll();
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new PrenotazioneException("Id non valido");
    }
}
