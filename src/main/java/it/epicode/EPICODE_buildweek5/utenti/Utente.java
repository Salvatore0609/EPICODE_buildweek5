package it.epicode.EPICODE_buildweek5.utenti;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Utente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String avatar;

    //tipo utente
    @Enumerated(EnumType.STRING)
    private TipoUtente tipoUtente;
}