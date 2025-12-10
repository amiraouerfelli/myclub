package tn.amiraislem.myclub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;

import java.util.Optional;

public interface MembreService {

    Membre createMembre(Membre membre);

    Membre updateMembre(Long id, Membre membre);

    void deleteMembre(Long id);

    Membre getMembreById(Long id);

    Page<Membre> getAllMembres(Pageable pageable);

    // Recherche globale
    Page<Membre> search(String keyword, Pageable pageable);

    // Filtres
    Page<Membre> getMembresByPoste(Long posteId, Pageable pageable);

    Page<Membre> getMembresByDepartement(Long departementId, Pageable pageable);

    // Tri
    Page<Membre> sortBy(String field, String direction, Pageable pageable);

    // Relations
    Page<Evenement> getEvenementsOfMembre(Long membreId, Pageable pageable);

    // Statistiques
    long countAll();
    long countByDepartement(Long departementId);

    // Vérifications email/téléphone uniques
    Optional<Membre> findByEmail(String email);

    Optional<Membre> findByTelephone(String telephone);
}
