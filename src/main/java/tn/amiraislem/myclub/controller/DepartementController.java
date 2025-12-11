package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Departement;
import tn.amiraislem.myclub.service.DepartementService;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    // ✔ CREATE
    @PostMapping
    public Departement create(@RequestBody Departement departement) {
        return departementService.save(departement);
    }

    // ✔ UPDATE
    @PutMapping("/{id}")
    public Departement update(@PathVariable Long id, @RequestBody Departement departement) {
        departement.setId(id);
        return departementService.update(departement);
    }

    // ✔ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departementService.deleteById(id);
    }

    // ✔ GET BY ID
    @GetMapping("/{id}")
    public Departement getById(@PathVariable Long id) {
        return departementService.findById(id);
    }

    // ✔ FIND BY NAME
    @GetMapping("/search")
    public Page<Departement> search(
            @RequestParam String nom,
            Pageable pageable
    ) {
        return departementService.findByNom(nom, pageable);
    }

    // ✔ ORDER ASC
    @GetMapping("/sort/asc")
    public Page<Departement> sortAsc(Pageable pageable) {
        return departementService.findAllByOrderByNomAsc(pageable);
    }

    // ✔ ORDER DESC
    @GetMapping("/sort/desc")
    public Page<Departement> sortDesc(Pageable pageable) {
        return departementService.findAllByOrderByNomDesc(pageable);
    }

    // ✔ STATS : total départements
    @GetMapping("/count")
    public long count() {
        return departementService.countTotal();
    }

    // ✔ STATS : nombre de membres d’un département
    @GetMapping("/{id}/count-membres")
    public long countMembres(@PathVariable Long id) {
        return departementService.countMembresByDepartement(id);
    }
}
