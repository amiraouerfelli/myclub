package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Activite;
import tn.amiraislem.myclub.entity.Membre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Set;

public interface ActiviteService {

    // CRUD
    Activite save(Activite activite);
    Activite update(Activite activite);
    void deleteById(Long id);
    Activite findById(Long id);

    // Pagination & recherche
    Page<Activite> findByTitre(String titre, Pageable pageable);
    Page<Activite> filter(LocalDate date, String lieu, Boolean terminee, Pageable pageable);

    // Tri
    Page<Activite> findAllOrderByTitreAsc(Pageable pageable);
    Page<Activite> findAllOrderByTitreDesc(Pageable pageable);
    Page<Activite> findAllOrderByDateAsc(Pageable pageable);
    Page<Activite> findAllOrderByDateDesc(Pageable pageable);

    // Participants
    Set<Membre> findParticipants(Long activiteId);
    long countParticipants(Long activiteId);

    // Statistiques
    long countTotal();
    long countByTerminee(boolean terminee);
}
