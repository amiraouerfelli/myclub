package tn.amiraislem.myclub.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.service.EvenementService;

import java.time.LocalDate;
import java.util.Set;


@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public Evenement create(@RequestBody Evenement evenement) {
        return evenementService.save(evenement);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public Evenement update(@PathVariable Long id, @RequestBody Evenement evenement) {
        evenement.setId(id);
        return evenementService.update(evenement);
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evenementService.deleteById(id);
    }

    // ---------------- FIND BY ID ----------------
    @GetMapping("/{id}")
    public Evenement getById(@PathVariable Long id) {
        return evenementService.findById(id);
    }

    // ---------------- SEARCH BY TITRE ----------------
    @GetMapping("/search")
    public Page<Evenement> searchByTitre(
            @RequestParam String titre,
            Pageable pageable
    ) {
        return evenementService.findByTitre(titre, pageable);
    }

    // ---------------- FILTER ----------------
    @GetMapping("/filter")
    public Page<Evenement> filter(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String lieu,
            @RequestParam(required = false) Boolean terminee,
            Pageable pageable
    ) {
        return evenementService.filter(date, lieu, terminee, pageable);
    }

    // ---------------- SORT ----------------
    @GetMapping("/sort/titre/asc")
    public Page<Evenement> sortTitreAsc(Pageable pageable) {
        return evenementService.findAllByOrderByTitreAsc(pageable);
    }

    @GetMapping("/sort/titre/desc")
    public Page<Evenement> sortTitreDesc(Pageable pageable) {
        return evenementService.findAllByOrderByTitreDesc(pageable);
    }

    @GetMapping("/sort/date/asc")
    public Page<Evenement> sortDateAsc(Pageable pageable) {
        return evenementService.findAllByOrderByDateAsc(pageable);
    }

    @GetMapping("/sort/date/desc")
    public Page<Evenement> sortDateDesc(Pageable pageable) {
        return evenementService.findAllByOrderByDateDesc(pageable);
    }

    // ---------------- PARTICIPANTS ----------------
    @GetMapping("/{id}/participants")
    public Set<Membre> getParticipants(@PathVariable Long id) {
        return evenementService.findParticipantsByEvenementId(id);
    }

    @GetMapping("/{id}/participants/count")
    public long countParticipants(@PathVariable Long id) {
        return evenementService.countParticipantsByEvenementId(id);
    }

    // ---------------- STATISTICS ----------------
    @GetMapping("/count")
    public long countTotal() {
        return evenementService.countTotal();
    }

    @GetMapping("/count/terminee")
    public long countByTerminee(@RequestParam boolean terminee) {
        return evenementService.countByTerminee(terminee);
    }
}
