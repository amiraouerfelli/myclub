package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Cotisation;
import tn.amiraislem.myclub.service.CotisationService;

@RestController
@RequestMapping("/api/cotisations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CotisationController {

    private final CotisationService cotisationService;

    public CotisationController(CotisationService cotisationService) {
        this.cotisationService = cotisationService;
    }

    // -------------------- CRUD --------------------

    @PostMapping
    public Cotisation create(@RequestBody Cotisation cotisation) {
        return cotisationService.save(cotisation);
    }

    @PutMapping("/{id}")
    public Cotisation update(@PathVariable Long id, @RequestBody Cotisation cotisation) {
        cotisation.setId(id);
        return cotisationService.update(cotisation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cotisationService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Cotisation getById(@PathVariable Long id) {
        return cotisationService.findById(id);
    }

    // -------------------- RECHERCHE SIMPLE --------------------

    @GetMapping("/membre/{membreId}")
    public Page<Cotisation> getByMembre(@PathVariable Long membreId, Pageable pageable) {
        return cotisationService.findByMembreId(membreId, pageable);
    }

    @GetMapping("/annee/{annee}")
    public Page<Cotisation> getByAnnee(@PathVariable Integer annee, Pageable pageable) {
        return cotisationService.findByAnnee(annee, pageable);
    }

    @GetMapping("/payee/{payee}")
    public Page<Cotisation> getByPayee(@PathVariable boolean payee, Pageable pageable) {
        return cotisationService.findByPayee(payee, pageable);
    }

    // -------------------- RECHERCHE MULTI-CRITÈRES --------------------

    @GetMapping("/filter")
    public Page<Cotisation> filter(
            @RequestParam(required = false) Long membreId,
            @RequestParam(required = false) Integer annee,
            @RequestParam(required = false) Boolean payee,
            Pageable pageable
    ) {
        return cotisationService.filter(membreId, annee, payee, pageable);
    }

    // -------------------- STATISTIQUES --------------------

    @GetMapping("/count")
    public long countTotal() {
        return cotisationService.countTotal();
    }

    @GetMapping("/count/payee/{payee}")
    public long countByPayee(@PathVariable boolean payee) {
        return cotisationService.countByPayee(payee);
    }

    @GetMapping("/montant/total")
    public Double montantTotal() {
        return cotisationService.totalMontantPayee();
    }
}
