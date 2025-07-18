package davidedangelo.U2_W2_D5.repository;

import davidedangelo.U2_W2_D5.entities.Postazione;
import davidedangelo.U2_W2_D5.entities.Prenotazione;
import davidedangelo.U2_W2_D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository <Prenotazione, Long> {
    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate data);
    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate data);
}
