package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Poste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PosteService {

    // CRUD
    Poste save(Poste poste);
    Poste update(Poste poste);
    void deleteById(Long id);
    Poste findById(Long id);

    // Recherche
    Page<Poste> findByNom(String nom, Pageable pageable);

    // Statistiques
    long count();
    long countMembresByPoste(Long posteId);

    // Récupérer tous les postes (tri)
    Page<Poste> findAllOrderByNomAsc(Pageable pageable);
    Page<Poste> findAllOrderByNomDesc(Pageable pageable);

    List<Poste> findAll(); // pour dropdowns ou affichage complet
}
