package tn.amiraislem.myclub.controller;

import tn.amiraislem.myclub.entity.Poste;
import tn.amiraislem.myclub.service.PosteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postes")
public class PosteController {

    private final PosteService posteService;

    public PosteController(PosteService posteService) {
        this.posteService = posteService;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public Poste create(@RequestBody Poste poste) {
        return posteService.save(poste);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public Poste update(@PathVariable Long id, @RequestBody Poste poste) {
        poste.setId(id);
        return posteService.update(poste);
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        posteService.deleteById(id);
    }

    // ---------------- FIND BY ID ----------------
    @GetMapping("/{id}")
    public Poste getById(@PathVariable Long id) {
        return posteService.findById(id);
    }

    // ---------------- SEARCH BY NOM ----------------
    @GetMapping("/search")
    public Page<Poste> searchByNom(@RequestParam String nom, Pageable pageable) {
        return posteService.findByNom(nom, pageable);
    }

    // ---------------- SORT ----------------
    @GetMapping("/sort/nom/asc")
    public Page<Poste> sortNomAsc(Pageable pageable) {
        return posteService.findAllOrderByNomAsc(pageable);
    }

    @GetMapping("/sort/nom/desc")
    public Page<Poste> sortNomDesc(Pageable pageable) {
        return posteService.findAllOrderByNomDesc(pageable);
    }

    // ---------------- STATISTICS ----------------
    @GetMapping("/count")
    public long countTotal() {
        return posteService.count();
    }

    @GetMapping("/{id}/membres/count")
    public long countMembresByPoste(@PathVariable Long id) {
        return posteService.countMembresByPoste(id);
    }

    // ---------------- LIST ALL ----------------
    @GetMapping("/all")
    public List<Poste> getAll() {
        return posteService.findAll();
    }
}
