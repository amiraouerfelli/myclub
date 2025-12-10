package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Activite;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.service.ActiviteService;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/activites")
@RequiredArgsConstructor
public class ActiviteController {

    private final ActiviteService activiteService;

    public ActiviteController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    // ---------------------- CRUD ----------------------

    @PostMapping
    public Activite create(@RequestBody Activite activite) {
        return activiteService.save(activite);
    }

    @PutMapping("/{id}")
    public Activite update(@PathVariable Long id, @RequestBody Activite activite) {
        activite.setId(id);
        return activiteService.update(activite);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        activiteService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Activite getById(@PathVariable Long id) {
        return activiteService.findById(id);
    }

    // ---------------------- Recherche ----------------------

    @GetMapping("/search")
    public Page<Activite> search(
            @RequestParam String titre,
            Pageable pageable
    ) {
        return activiteService.findByTitre(titre, pageable);
    }

    // ---------------------- Filtres ----------------------

    @GetMapping("/filter")
    public Page<Activite> filter(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String lieu,
            @RequestParam(required = false) Boolean terminee,
            Pageable pageable
    ) {
        return activiteService.filter(date, lieu, terminee, pageable);
    }

    // ---------------------- Tri ----------------------

    @GetMapping("/sort")
    public Page<Activite> sort(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction,
            Pageable pageable
    ) {
        switch (field.toLowerCase()) {
            case "titre":
                return direction.equalsIgnoreCase("asc") ?
                        activiteService.findAllOrderByTitreAsc(pageable) :
                        activiteService.findAllOrderByTitreDesc(pageable);

            case "date":
                return direction.equalsIgnoreCase("asc") ?
                        activiteService.findAllOrderByDateAsc(pageable) :
                        activiteService.findAllOrderByDateDesc(pageable);

            default:
                throw new RuntimeException("Champ de tri invalide !");
        }
    }

    // ---------------------- Participants ----------------------

    @GetMapping("/{id}/participants")
    public Set<Membre> getParticipants(@PathVariable Long id) {
        return activiteService.findParticipants(id);
    }

    @GetMapping("/{id}/participants/count")
    public long countParticipants(@PathVariable Long id) {
        return activiteService.countParticipants(id);
    }

    // ---------------------- Statistiques ----------------------

    @GetMapping("/stats/count")
    public long countAll() {
        return activiteService.countTotal();
    }

    @GetMapping("/stats/count/terminee")
    public long countByTerminee(@RequestParam boolean terminee) {
        return activiteService.countByTerminee(terminee);
    }
}
