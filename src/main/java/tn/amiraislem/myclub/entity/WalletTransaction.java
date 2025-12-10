package tn.amiraislem.myclub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transaction")
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Type { CREDIT, DEBIT }

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Type type;

    @NotNull
    @Positive(message = "Le montant doit être positif")
    @Column(nullable = false)
    private BigDecimal montant;

    @NotNull(message = "La raison de la transaction est obligatoire")
    @Column(nullable = false)
    private String raison; // ex: "cotisation 2025", "achat matériel", "sponsor X"

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateTransaction;

    public WalletTransaction() {}

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public String getRaison() { return raison; }
    public void setRaison(String raison) { this.raison = raison; }

    public LocalDateTime getDateTransaction() { return dateTransaction; }
    public void setDateTransaction(LocalDateTime dateTransaction) { this.dateTransaction = dateTransaction; }
}
