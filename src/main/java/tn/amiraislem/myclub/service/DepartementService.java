package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartementService {

    // CRUD
    Departement save(Departement departement);
    Departement update(Departement departement);
    void deleteById(Long id);
    Departement findById(Long id);

    // Recherche par nom
    Page<Departement> findByNom(String nom, Pageable pageable);

    // Statistiques
    long countTotal();
    long countMembresByDepartement(Long departementId);

    // Tri
    Page<Departement> findAllByOrderByNomAsc(Pageable pageable);
    Page<Departement> findAllByOrderByNomDesc(Pageable pageable);
}
