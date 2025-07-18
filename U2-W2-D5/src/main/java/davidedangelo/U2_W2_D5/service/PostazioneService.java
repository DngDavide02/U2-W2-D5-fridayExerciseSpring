package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.Enum.TipoPostazione;
import davidedangelo.U2_W2_D5.entities.Postazione;
import davidedangelo.U2_W2_D5.exception.PostazioneException;
import davidedangelo.U2_W2_D5.repository.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public Postazione savePostazione(Postazione postazione){
        try {
            postazioneValida(postazione);
            return postazioneRepository.save(postazione);
        } catch (PostazioneException e) {
            throw e;
        } catch (Exception e) {
            throw new PostazioneException("Errore, riprova");
        }
    }

    public List<Postazione> findByTipoAndCittaIgnoreCase(TipoPostazione tipo, String citta) {
        try {
            if (tipo == null) throw new PostazioneException("Tipo obbligatorio");
            if (isNullOrBlank(citta)) throw new PostazioneException("Città obbligatoria");
            return postazioneRepository.findByTipoAndEdificio_CittaIgnoreCase(tipo, citta);
        } catch (PostazioneException e) {
            throw e;
        } catch (Exception e) {
            throw new PostazioneException("Errore, riprova");
        }
    }

    public Optional<Postazione> findById(Long id){
        try {
            idValido(id);
            return postazioneRepository.findById(id);
        } catch (PostazioneException e) {
            throw e;
        } catch (Exception e) {
            throw new PostazioneException("Errore, riprova");
        }
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    private void postazioneValida(Postazione postazione) {
        if (postazione == null) throw new PostazioneException("Postazione null");
        if (isNullOrBlank(postazione.getCodice())) throw new PostazioneException("Codice obbligatorio");
        if (postazione.getTipo() == null) throw new PostazioneException("Tipo obbligatorio");
        if (postazione.getMaxOccupanti() <= 0) throw new PostazioneException("Occupanti devono essere > 0");
        if (postazione.getEdificio() == null) throw new PostazioneException("Edificio obbligatorio");
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new PostazioneException("Id non valido");
    }
}
