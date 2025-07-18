package davidedangelo.U2_W2_D5.repository;

import davidedangelo.U2_W2_D5.Enum.TipoPostazione;
import davidedangelo.U2_W2_D5.entities.Edificio;
import davidedangelo.U2_W2_D5.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository <Postazione, Long> {
    List<Postazione> findByTipoAndCitta(TipoPostazione tipo, String citta);
}
