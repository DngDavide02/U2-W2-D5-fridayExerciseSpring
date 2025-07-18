package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.Enum.TipoPostazione;
import davidedangelo.U2_W2_D5.entities.Postazione;
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
        postazioneValida(postazione);
        return postazioneRepository.save(postazione);
    }

    public List<Postazione> findByTipoAndCittaIgnoreCase(TipoPostazione tipo, String citta) {
        if (tipo == null) throw new IllegalArgumentException("Tipo obbligatorio");
        if (isNullOrBlank(citta)) throw new IllegalArgumentException("Città obbligatoria");
        return postazioneRepository.findByTipoAndEdificio_CittaIgnoreCase(tipo, citta);
    }

    public Optional<Postazione> findById(Long id){
        idValido(id);
        return postazioneRepository.findById(id);
    }

    private boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    private void postazioneValida(Postazione p) {
        if (p == null) throw new IllegalArgumentException("Postazione null");
        if (isNullOrBlank(p.getCodice())) throw new IllegalArgumentException("Codice obbligatorio");
        if (p.getTipo() == null) throw new IllegalArgumentException("Tipo obbligatorio");
        if (p.getMaxOccupanti() <= 0) throw new IllegalArgumentException("Occupanti devono essere > 0");
        if (p.getEdificio() == null) throw new IllegalArgumentException("Edificio obbligatorio");
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id non valido");
    }
}
