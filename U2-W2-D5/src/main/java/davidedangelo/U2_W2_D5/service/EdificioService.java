package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Edificio;
import davidedangelo.U2_W2_D5.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio saveEdificio(Edificio edificio){
        edificioValido(edificio);
        return edificioRepository.save(edificio);
    }

    public List<Edificio> findAll(){
        return edificioRepository.findAll();
    }

    public Optional<Edificio> findById(Long id){
        idValido(id);
        return edificioRepository.findById(id);
    }

    public void deleteById(Long id){
        idValido(id);
        edificioRepository.deleteById(id);
    }

    private boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    private void edificioValido(Edificio e) {
        if (e == null) throw new IllegalArgumentException("Edificio null");
        if (isNullOrBlank(e.getNome())) throw new IllegalArgumentException("Nome obbligatorio");
        if (isNullOrBlank(e.getIndirizzo())) throw new IllegalArgumentException("Indirizzo obbligatorio");
        if (isNullOrBlank(e.getCitta())) throw new IllegalArgumentException("Città obbligatoria");
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id non valido");
    }
}
