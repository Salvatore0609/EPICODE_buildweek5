package it.epicode.EPICODE_buildweek5.clienti;

import com.github.javafaker.Faker;
import it.epicode.EPICODE_buildweek5.auth.Role;
import it.epicode.EPICODE_buildweek5.fatture.Fattura;
import it.epicode.EPICODE_buildweek5.fatture.FatturaRepository;
import it.epicode.EPICODE_buildweek5.fatture.StatoFattura;
import it.epicode.EPICODE_buildweek5.fatture.StatoFatturaRepository;
import it.epicode.EPICODE_buildweek5.indirizzi.*;
import it.epicode.EPICODE_buildweek5.utenti.Utente;
import it.epicode.EPICODE_buildweek5.utenti.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClienteRunner implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final IndirizzoLegaleRepository IndirizzoLegaleRepository;
    private final IndirizzoOperativaRepository IndirizzoOperativaRepository;
    private final ComuneRepository comuneRepository;
    private final FatturaRepository fatturaRepository;
    private final StatoFatturaRepository statoFatturaRepository;
    private final Faker faker;
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        List<Comune> comuni = comuneRepository.findAll();
        if (comuni.isEmpty()) {
            throw new IllegalStateException("Nessun comune trovato nel DB.");
        }

        for (int i = 0; i < 10; i++) {

            Comune comuneSelezionato = comuni.get(faker.random().nextInt(comuni.size()));

        IndirizzoLegale indirizzoLegale = new IndirizzoLegale();
        indirizzoLegale.setVia(faker.address().streetName());
        indirizzoLegale.setCivico(faker.address().streetAddressNumber());
        indirizzoLegale.setCap(faker.address().zipCode());
        indirizzoLegale.setLocalita( comuneSelezionato.getDenominazioneInItaliano() + ", " + comuneSelezionato.getNomeProvincia());
        indirizzoLegale.setComune(comuneSelezionato);
        indirizzoLegale = IndirizzoLegaleRepository.save(indirizzoLegale);

        IndirizzoOperativa indirizzoOperativa = new IndirizzoOperativa();
        indirizzoOperativa.setVia(faker.address().streetName());
        indirizzoOperativa.setCivico(faker.address().streetAddressNumber());
        indirizzoOperativa.setCap(faker.address().zipCode());
        indirizzoOperativa.setLocalita(  comuneSelezionato.getDenominazioneInItaliano() + ", " + comuneSelezionato.getNomeProvincia());
        indirizzoOperativa.setComune(comuneSelezionato);
        indirizzoOperativa = IndirizzoOperativaRepository.save(indirizzoOperativa);

            TipoCliente tipo = TipoCliente.values()[faker.random().nextInt(TipoCliente.values().length)];

            Cliente cliente = new Cliente();
            cliente.setRagioneSociale(faker.company().name());
            cliente.setPartitaIva(faker.number().digits(11));
            cliente.setEmail(faker.internet().emailAddress());
            cliente.setPec(faker.internet().emailAddress());
            cliente.setTelefono(faker.phoneNumber().phoneNumber());
            cliente.setEmailContatto(faker.name().firstName() + "." + faker.name().lastName() + "@example.com");
            cliente.setNomeContatto(faker.name().firstName());
            cliente.setCognomeContatto(faker.name().lastName());
            cliente.setTelefonoContatto(faker.phoneNumber().phoneNumber());
            cliente.setLogoAziendale(faker.company().logo());
            cliente.setFatturatoAnnuale(faker.number().randomDouble(2, 100000, 10000000));
            cliente.setDataInserimento(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            cliente.setDataUltimoContatto(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            cliente.setTipoCliente(tipo);


            cliente.setIndirizzoLegale(indirizzoLegale);
            cliente.setIndirizzoOperativa(indirizzoOperativa);
            clienteRepository.save(cliente);

            List<Fattura> fattureCliente = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                StatoFattura statoFattura = new StatoFattura();
                statoFattura.setNome(StatoFattura.statiFattura.get(faker.random().nextInt(StatoFattura.statiFattura.size())));
                statoFattura = statoFatturaRepository.save(statoFattura);

                Fattura fattura = new Fattura();
                fattura.setData(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                fattura.setAnno(fattura.getData().getYear());
                fattura.setNumero(faker.number().numberBetween(1, 1000));
                fattura.setRangeDiImporti(faker.number().randomDouble(2, 100, 1000));
                fattura.setStatoFattura(statoFattura);
                fattura.setCliente(cliente);
                fattura = fatturaRepository.save(fattura);

                fattureCliente.add(fattura);
            }

            cliente.setFattura(fattureCliente);
            clienteRepository.save(cliente);


        }
        Utente admin = new Utente();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("adminpwd"));
        admin.setRoles(Set.of(Role.ROLE_ADMIN));
        utenteRepository.save(admin);
    }
}
