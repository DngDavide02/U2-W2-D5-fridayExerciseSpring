package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Edificio;
import davidedangelo.U2_W2_D5.exception.EdificioException;
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
        try {
            edificioValido(edificio);
            return edificioRepository.save(edificio);
        } catch (EdificioException e) {
            throw e;
        } catch (Exception e) {
            throw new EdificioException("Errore, riprova");
        }
    }

    public List<Edificio> findAll(){
        try {
            return edificioRepository.findAll();
        } catch (Exception e) {
            throw new EdificioException("Errore, riprova");
        }
    }

    public Optional<Edificio> findById(Long id){
        try {
            idValido(id);
            return edificioRepository.findById(id);
        } catch (EdificioException e) {
            throw e;
        } catch (Exception e) {
            throw new EdificioException("Errore, riprova");
        }
    }

    public void deleteById(Long id){
        try {
            idValido(id);
            edificioRepository.deleteById(id);
        } catch (EdificioException e) {
            throw e;
        } catch (Exception e) {
            throw new EdificioException("Errore, riprova");
        }
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    private void edificioValido(Edificio edificio) {
        if (edificio == null) throw new EdificioException("Edificio null");
        if (isNullOrBlank(edificio.getNome())) throw new EdificioException("Nome obbligatorio");
        if (isNullOrBlank(edificio.getIndirizzo())) throw new EdificioException("Indirizzo obbligatorio");
        if (isNullOrBlank(edificio.getCitta())) throw new EdificioException("Città obbligatoria");
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new EdificioException("Id non valido");
    }
}
