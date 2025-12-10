package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Parametres;
import tn.amiraislem.myclub.service.ParametresService;

import java.util.List;

@RestController
@RequestMapping("/api/parametres")
@RequiredArgsConstructor
public class ParametresController {

    private final ParametresService parametresService;

    public ParametresController(ParametresService parametresService) {
        this.parametresService = parametresService;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public Parametres create(@RequestBody Parametres parametres) {
        return parametresService.save(parametres);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public Parametres update(@PathVariable Long id, @RequestBody Parametres parametres) {
        parametres.setId(id);
        return parametresService.update(parametres);
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parametresService.deleteById(id);
    }

    // ---------------- FIND BY ID ----------------
    @GetMapping("/{id}")
    public Parametres getById(@PathVariable Long id) {
        return parametresService.findById(id);
    }

    // ---------------- FIND ALL ----------------
    @GetMapping
    public List<Parametres> getAll() {
        return parametresService.findAll();
    }
}
