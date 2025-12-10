package tn.amiraislem.myclub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom ou organisation (au moins un des deux doit être rempli)
    private String nom;

    private String organisation;

    @Email(message = "Email invalide")
    private String email;

    private String telephone;

    @NotBlank(message = "Le rôle est obligatoire")
    @Column(nullable = false)
    private String role;  // SPEAKER, SPONSOR, INVITE...

    private String notes;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Contact() {}

    // --- VALIDATION METIER (nom ou organisation obligatoire)
    @PrePersist
    @PreUpdate
    private void validateNomOuOrganisation() {
        if ((nom == null || nom.isBlank()) &&
                (organisation == null || organisation.isBlank())) {

            throw new IllegalStateException(
                    "Le contact doit avoir un nom ou une organisation."
            );
        }

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // --- Getters / setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getOrganisation() { return organisation; }
    public void setOrganisation(String organisation) { this.organisation = organisation; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
