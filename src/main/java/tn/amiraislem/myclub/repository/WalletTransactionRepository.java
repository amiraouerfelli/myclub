package tn.amiraislem.myclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

    // ----------------------------------
    // RECHERCHES
    // ----------------------------------

    // Recherche par type (CREDIT ou DEBIT)
    Page<WalletTransaction> findByType(WalletTransaction.Type type, Pageable pageable);

    // Recherche par raison (nom ou organisation dans la phrase)
    Page<WalletTransaction> findByRaisonContainingIgnoreCase(String raison, Pageable pageable);

    // ----------------------------------
    // CALCUL DU SOLDE ACTUEL DU CLUB
    // ----------------------------------

    // Somme des crédits
    @Query("SELECT COALESCE(SUM(w.montant), 0) FROM WalletTransaction w WHERE w.type = 'CREDIT'")
    Double totalCredits();

    // Somme des débits
    @Query("SELECT COALESCE(SUM(w.montant), 0) FROM WalletTransaction w WHERE w.type = 'DEBIT'")
    Double totalDebits();

    // Solde final = crédits – débits
    @Query("""
        SELECT 
            (SELECT COALESCE(SUM(w1.montant), 0) FROM WalletTransaction w1 WHERE w1.type = 'CREDIT')
            -
            (SELECT COALESCE(SUM(w2.montant), 0) FROM WalletTransaction w2 WHERE w2.type = 'DEBIT')
        """)
    Double soldeActuel();
}
