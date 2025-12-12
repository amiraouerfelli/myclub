package tn.amiraislem.myclub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "membre")
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(nullable = false)
    private String prenom;

    // Relation vers Departement (nullable car par ex. président sans département)
    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = true)
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "poste_id", nullable = false)
    private Poste poste;

    @NotBlank(message = "Le niveau est obligatoire")
    @Column(nullable = false)
    private String niveau;

    @NotBlank(message = "La filière est obligatoire")
    @Column(nullable = false)
    private String filiere;

    @Email(message = "Email invalide")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Column(unique = true, nullable = false)
    private String telephone;

    @NotBlank(message = "L'adresse est obligatoire")
    @Column(nullable = false)
    private String adresse;

    @NotNull(message = "La date d'adhésion est obligatoire")
    @Column(nullable = false)
    private LocalDate dateAdhesion;

    @OneToMany(mappedBy = "membre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cotisation> cotisations = new ArrayList<>();

    @NotNull
    @Column(nullable = true)
    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "membre_evenement",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "evenement_id")
    )
    private Set<Evenement> evenementsInscrits = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "membre_activite",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "activite_id")
    )
    private Set<Activite> activitesAttended = new HashSet<>();

    // --- Constructeur vide requis par JPA ---
    public Membre() {}

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }

    public Poste getPoste() { return poste; }
    public void setPoste(Poste poste) { this.poste = poste; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public LocalDate getDateAdhesion() { return dateAdhesion; }
    public void setDateAdhesion(LocalDate dateAdhesion) { this.dateAdhesion = dateAdhesion; }

    public List<Cotisation> getCotisations() { return cotisations; }
    public void setCotisations(List<Cotisation> cotisations) { this.cotisations = cotisations; }

    public List<Cotisation> getCotisationsAnnee(int annee) {
        return cotisations.stream()
                .filter(c -> c.getAnnee() == annee)
                .collect(Collectors.toList());
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Set<Evenement> getEvenementsInscrits() { return evenementsInscrits; }
    public void setEvenementsInscrits(Set<Evenement> evenementsInscrits) { this.evenementsInscrits = evenementsInscrits; }

    public Set<Activite> getActivitesAttended() { return activitesAttended; }
    public void setActivitesAttended(Set<Activite> activitesAttended) { this.activitesAttended = activitesAttended; }
}
