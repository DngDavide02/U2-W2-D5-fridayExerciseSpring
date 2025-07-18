package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Utente;
import davidedangelo.U2_W2_D5.exception.UtenteException;
import davidedangelo.U2_W2_D5.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente saveUtente(Utente utente){
        try {
            validaUtente(utente);
            return utenteRepository.save(utente);
        } catch (UtenteException e) {
            throw e;
        } catch (Exception e) {
            throw new UtenteException("Errore, riprova");
        }
    }

    public List<Utente> findAll(){
        try {
            return utenteRepository.findAll();
        } catch (Exception e) {
            throw new UtenteException("Errore, riprova");
        }
    }

    public Optional<Utente> findById(Long id){
        try {
            idValido(id);
            return utenteRepository.findById(id);
        } catch (UtenteException e) {
            throw e;
        } catch (Exception e) {
            throw new UtenteException("Errore, riprova");
        }
    }

    public Optional<Utente> findByUsernameIgnoreCase(String username){
        try {
            if (isNullOrBlank(username)) throw new UtenteException("Username obbligatorio");
            return utenteRepository.findByUsernameIgnoreCase(username);
        } catch (UtenteException e) {
            throw e;
        } catch (Exception e) {
            throw new UtenteException("Errore, riprova");
        }
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new UtenteException("Id non valido");
    }

    private void validaUtente(Utente utente) {
        if (utente == null) throw new UtenteException("Utente null");
        if (isNullOrBlank(utente.getUsername())) throw new UtenteException("Username obbligatorio");
        if (isNullOrBlank(utente.getNome())) throw new UtenteException("Nome obbligatorio");
        if (isNullOrBlank(utente.getEmail())) throw new UtenteException("Email obbligatoria");
    }
}
