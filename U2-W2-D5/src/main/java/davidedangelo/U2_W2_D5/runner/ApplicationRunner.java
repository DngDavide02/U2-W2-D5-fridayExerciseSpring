package davidedangelo.U2_W2_D5.runner;

import davidedangelo.U2_W2_D5.Enum.TipoPostazione;
import davidedangelo.U2_W2_D5.entities.Edificio;
import davidedangelo.U2_W2_D5.entities.Postazione;
import davidedangelo.U2_W2_D5.entities.Utente;
import davidedangelo.U2_W2_D5.service.EdificioService;
import davidedangelo.U2_W2_D5.service.PostazioneService;
import davidedangelo.U2_W2_D5.service.PrenotazioneService;
import davidedangelo.U2_W2_D5.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner{

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;


    @Override
    public void run(String... args) throws Exception {
        /*
        Edificio e1 = edificioService.saveEdificio(new Edificio("Sede Nord", "Via Milano 1", "Milano"));
        Edificio e2 = edificioService.saveEdificio(new Edificio("Sede Centro", "Via Firenze 2", "Firenze"));
        Edificio e3 = edificioService.saveEdificio(new Edificio("Sede Sud", "Via Napoli 3", "Napoli"));

        Postazione p1 = postazioneService.savePostazione(new Postazione("P1", "Open space Milano", TipoPostazione.OPENSPACE, 4, e1));
        Postazione p2 = postazioneService.savePostazione(new Postazione("P2", "Privata Firenze", TipoPostazione.PRIVATO, 1, e2));
        Postazione p3 = postazioneService.savePostazione(new Postazione("P3", "Sala riunioni Napoli", TipoPostazione.SALA_RIUNIONI, 10, e3));

        Utente u1 = utenteService.saveUtente(new Utente("luca.verdi", "Luca Verdi", "luca@gmail.com"));
        Utente u2 = utenteService.saveUtente(new Utente("giulia.bianchi", "Giulia Bianchi", "giulia@libero.it"));
        Utente u3 = utenteService.saveUtente(new Utente("marco.neri", "Marco Neri", "marco@yahoo.it"));

        prenotazioneService.prenota(u1.getUsername(), p1.getId(), LocalDate.now().plusDays(1));
        prenotazioneService.prenota(u2.getUsername(), p2.getId(), LocalDate.now().plusDays(2));
        prenotazioneService.prenota(u3.getUsername(), p3.getId(), LocalDate.now().plusDays(3));
        System.out.println("Prenotazioni create con successo");*/

        //-----------------------------------------test metodi-------------------------------------------------
        System.out.println("--------------------edificio service----------------------------");
        edificioService.findAll().forEach(System.out::println);
        edificioService.findById(3L).ifPresent(System.out::println);
        System.out.println();
        System.out.println("--------------------postazione service--------------------------");
        List<Postazione> results = postazioneService.findByTipoAndCittaIgnoreCase(TipoPostazione.PRIVATO, "firenze");
        results.forEach(System.out::println);
        postazioneService.findById(3L).ifPresent(System.out::println);
        System.out.println();
        System.out.println("--------------------------prenotazione service-----------------------");
        prenotazioneService.findAllPrenotazioni().forEach(System.out::println);
        //prenotazioneService.prenota("Luca.Verdi", 2L, LocalDate.now());
        System.out.println();
        System.out.println("------------------------utente service--------------------------------");
        utenteService.findAll().forEach(System.out::println);
        utenteService.findByUsernameIgnoreCase("marco.neri");
        utenteService.findById(2L);
        System.out.println();

    }
}
