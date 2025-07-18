package davidedangelo.U2_W2_D5.service;

import davidedangelo.U2_W2_D5.entities.Utente;
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
        validaUtente(utente);
        return utenteRepository.save(utente);
    }

    public List<Utente> findAll(){
        return utenteRepository.findAll();
    }

    public Optional<Utente> findById(Long id){
        idValido(id);
        return utenteRepository.findById(id);
    }

    public Optional<Utente> findByUsernameIgnoreCase(String username){
        if (isNullOrBlank(username)) throw new IllegalArgumentException("username obbligatorio");
        return utenteRepository.findByUsernameIgnoreCase(username);
    }

    private boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    private void idValido(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id non valido");
    }

    private void validaUtente(Utente u) {
        if (u == null) throw new IllegalArgumentException("Utente null");
        if (isNullOrBlank(u.getUsername())) throw new IllegalArgumentException("Username obbligatorio");
        if (isNullOrBlank(u.getNome())) throw new IllegalArgumentException("Nome obbligatorio");
        if (isNullOrBlank(u.getEmail())) throw new IllegalArgumentException("Email obbligatoria");
    }
}
