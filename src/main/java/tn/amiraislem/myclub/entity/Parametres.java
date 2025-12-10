package tn.amiraislem.myclub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parametres")
public class Parametres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom du club → obligatoire
    @Column(nullable = false)
    @NotNull
    private String nomClub;

    // Nombre de cotisations par an → obligatoire
    @Column(nullable = false)
    @NotNull
    private Integer nombreCotisationsParAn;

    // Logo du club (chemin ou URL) → facultatif
    private String logo;

    // Départements → obligatoire au moins un
    @OneToMany
    @JoinColumn(name = "parametres_id")
    @NotNull
    private Set<Departement> departements = new HashSet<>();

    // Postes → obligatoire au moins un
    @OneToMany
    @JoinColumn(name = "parametres_id")
    @NotNull
    private Set<Poste> postes = new HashSet<>();

    // --- Constructeurs ---
    public Parametres() {}

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomClub() { return nomClub; }
    public void setNomClub(String nomClub) { this.nomClub = nomClub; }

    public Integer getNombreCotisationsParAn() { return nombreCotisationsParAn; }
    public void setNombreCotisationsParAn(Integer nombreCotisationsParAn) { this.nombreCotisationsParAn = nombreCotisationsParAn; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public Set<Departement> getDepartements() { return departements; }
    public void setDepartements(Set<Departement> departements) { this.departements = departements; }

    public Set<Poste> getPostes() { return postes; }
    public void setPostes(Set<Poste> postes) { this.postes = postes; }
}
