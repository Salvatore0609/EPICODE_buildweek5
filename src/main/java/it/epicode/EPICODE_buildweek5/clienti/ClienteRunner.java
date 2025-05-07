package it.epicode.EPICODE_buildweek5.clienti;

import com.github.javafaker.Faker;
import it.epicode.EPICODE_buildweek5.indirizzi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClienteRunner implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final IndirizzoLegaleRepository IndirizzoLegaleRepository;
    private final IndirizzoOperativaRepository IndirizzoOperativaRepository;
    private final ComuneRepository comuneRepository;
    private final Faker faker;

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
            cliente.setTipoCliente(tipo);


            cliente.setIndirizzoLegale(indirizzoLegale);
            cliente.setIndirizzoOperativa(indirizzoOperativa);
            clienteRepository.save(cliente);
        }
    }
}
