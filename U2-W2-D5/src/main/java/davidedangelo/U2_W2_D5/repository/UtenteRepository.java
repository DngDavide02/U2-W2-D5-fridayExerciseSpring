package davidedangelo.U2_W2_D5.repository;

import davidedangelo.U2_W2_D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository <Utente, Long> {
    Optional<Utente> findByUsernameIgnoreCase(String username);
}
