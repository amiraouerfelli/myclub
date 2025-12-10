package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import tn.amiraislem.myclub.entity.WalletTransaction;
import tn.amiraislem.myclub.repository.WalletTransactionRepository;
import tn.amiraislem.myclub.service.WalletTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionServiceImpl(WalletTransactionRepository walletTransactionRepository) {
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public WalletTransaction save(WalletTransaction transaction) {
        return walletTransactionRepository.save(transaction);
    }

    @Override
    public WalletTransaction update(WalletTransaction transaction) {
        return walletTransactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        walletTransactionRepository.deleteById(id);
    }

    @Override
    public WalletTransaction findById(Long id) {
        return walletTransactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction non trouvée avec l'id : " + id));
    }

    @Override
    public Page<WalletTransaction> findByType(WalletTransaction.Type type, Pageable pageable) {
        return walletTransactionRepository.findByType(type, pageable);
    }

    @Override
    public Page<WalletTransaction> findByRaison(String raison, Pageable pageable) {
        return walletTransactionRepository.findByRaisonContainingIgnoreCase(raison, pageable);
    }

    @Override
    public Double totalCredits() {
        return walletTransactionRepository.totalCredits();
    }

    @Override
    public Double totalDebits() {
        return walletTransactionRepository.totalDebits();
    }

    @Override
    public Double soldeActuel() {
        return walletTransactionRepository.soldeActuel();
    }

    @Override
    public List<WalletTransaction> findAll() {
        return walletTransactionRepository.findAll();
    }
}
