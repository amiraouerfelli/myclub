package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.service.MembreService;

@RestController
@RequestMapping("/api/membres")
@CrossOrigin("*")
public class MembreController {

    private final MembreService membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    // -----------------------------
    // Création
    // -----------------------------
    @PostMapping
    public Membre create(@RequestBody Membre membre) {
        return membreService.createMembre(membre);
    }

    // -----------------------------
    // Mise à jour
    // -----------------------------
    @PutMapping("/{id}")
    public Membre update(@PathVariable Long id, @RequestBody Membre membre) {
        return membreService.updateMembre(id, membre);
    }

    // -----------------------------
    // Suppression
    // -----------------------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        membreService.deleteMembre(id);
    }

    // -----------------------------
    // Récupérer un membre
    // -----------------------------
    @GetMapping("/{id}")
    public Membre getById(@PathVariable Long id) {
        return membreService.getMembreById(id);
    }

    // -----------------------------
    // Liste paginée
    // -----------------------------
    @GetMapping
    public Page<Membre> getAll(Pageable pageable) {
        return membreService.getAllMembres(pageable);
    }

    // -----------------------------
    // Recherche globale
    // -----------------------------
    @GetMapping("/search")
    public Page<Membre> search(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        return membreService.search(keyword, pageable);
    }

    // -----------------------------
    // Filtrer par poste
    // -----------------------------
    @GetMapping("/poste/{posteId}")
    public Page<Membre> getByPoste(
            @PathVariable Long posteId,
            Pageable pageable
    ) {
        return membreService.getMembresByPoste(posteId, pageable);
    }

    // -----------------------------
    // Filtrer par département
    // -----------------------------
    @GetMapping("/departement/{departementId}")
    public Page<Membre> getByDepartement(
            @PathVariable Long departementId,
            Pageable pageable
    ) {
        return membreService.getMembresByDepartement(departementId, pageable);
    }

    // -----------------------------
    // Tri dynamique
    // -----------------------------
    @GetMapping("/sort")
    public Page<Membre> sort(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction,
            Pageable pageable
    ) {
        return membreService.sortBy(field, direction, pageable);
    }

    // -----------------------------
    // Événements d’un membre
    // -----------------------------
    @GetMapping("/{id}/evenements")
    public Page<Evenement> getEvenements(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return membreService.getEvenementsOfMembre(id, pageable);
    }

    // -----------------------------
    // Compter les membres
    // -----------------------------
    @GetMapping("/count")
    public long count() {
        return membreService.countAll();
    }

    @GetMapping("/count/departement/{departementId}")
    public long countByDepartement(@PathVariable Long departementId) {
        return membreService.countByDepartement(departementId);
    }
}
