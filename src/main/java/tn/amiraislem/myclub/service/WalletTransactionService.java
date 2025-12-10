package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.WalletTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WalletTransactionService {

    // CRUD
    WalletTransaction save(WalletTransaction transaction);
    WalletTransaction update(WalletTransaction transaction);
    void deleteById(Long id);
    WalletTransaction findById(Long id);

    // Recherche
    Page<WalletTransaction> findByType(WalletTransaction.Type type, Pageable pageable);
    Page<WalletTransaction> findByRaison(String raison, Pageable pageable);

    // Statistiques / Solde
    Double totalCredits();
    Double totalDebits();
    Double soldeActuel();

    // Liste complète (pour affichage ou export)
    List<WalletTransaction> findAll();
}
