package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Parametres;

import java.util.List;

public interface ParametresService {

    // CRUD de base
    Parametres save(Parametres parametres);
    Parametres update(Parametres parametres);
    void deleteById(Long id);
    Parametres findById(Long id);

    // Récupérer toutes les configurations (normalement une seule)
    List<Parametres> findAll();
}
