package tn.amiraislem.myclub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotisation")
public class Cotisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // année de la cotisation (ex : 2025) → obligatoire
    @NotNull
    @Column(nullable = false)
    private Integer annee;

    // montant demandé cette année → obligatoire et positif
    @NotNull
    @Positive(message = "Le montant doit être positif")
    @Column(nullable = false)
    private BigDecimal montant;

    // si le membre a payé ou non (par défaut false)
    private boolean payee = false;

    // date de paiement → facultatif
    private LocalDate datePaiement;

    // membre → obligatoire
    @ManyToOne
    @JoinColumn(name = "membre_id", nullable = false)
    @NotNull
    private Membre membre;

    // createdAt pour historique → obligatoire
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Cotisation() {}

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public boolean isPayee() { return payee; }
    public void setPayee(boolean payee) { this.payee = payee; }

    public LocalDate getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDate datePaiement) { this.datePaiement = datePaiement; }

    public Membre getMembre() { return membre; }
    public void setMembre(Membre membre) { this.membre = membre; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
