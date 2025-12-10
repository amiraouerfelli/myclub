package tn.amiraislem.myclub.controller;

import tn.amiraislem.myclub.entity.WalletTransaction;
import tn.amiraislem.myclub.service.WalletTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet-transactions")
public class WalletTransactionController {

    private final WalletTransactionService walletTransactionService;

    // Injection via constructeur
    public WalletTransactionController(WalletTransactionService walletTransactionService) {
        this.walletTransactionService = walletTransactionService;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public WalletTransaction create(@RequestBody WalletTransaction transaction) {
        return walletTransactionService.save(transaction);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public WalletTransaction update(@PathVariable Long id, @RequestBody WalletTransaction transaction) {
        transaction.setId(id);
        return walletTransactionService.update(transaction);
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        walletTransactionService.deleteById(id);
    }

    // ---------------- FIND BY ID ----------------
    @GetMapping("/{id}")
    public WalletTransaction getById(@PathVariable Long id) {
        return walletTransactionService.findById(id);
    }

    // ---------------- SEARCH ----------------
    @GetMapping("/search/type")
    public Page<WalletTransaction> searchByType(
            @RequestParam WalletTransaction.Type type,
            Pageable pageable
    ) {
        return walletTransactionService.findByType(type, pageable);
    }

    @GetMapping("/search/raison")
    public Page<WalletTransaction> searchByRaison(
            @RequestParam String raison,
            Pageable pageable
    ) {
        return walletTransactionService.findByRaison(raison, pageable);
    }

    // ---------------- STATISTICS ----------------
    @GetMapping("/total-credits")
    public Double totalCredits() {
        return walletTransactionService.totalCredits();
    }

    @GetMapping("/total-debits")
    public Double totalDebits() {
        return walletTransactionService.totalDebits();
    }

    @GetMapping("/solde-actuel")
    public Double soldeActuel() {
        return walletTransactionService.soldeActuel();
    }

    // ---------------- LIST ALL ----------------
    @GetMapping("/all")
    public List<WalletTransaction> getAll() {
        return walletTransactionService.findAll();
    }
}
