package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Set;

public interface EvenementService {

    // CRUD
    Evenement save(Evenement evenement);
    Evenement update(Evenement evenement);
    void deleteById(Long id);
    Evenement findById(Long id);

    // Recherche par titre
    Page<Evenement> findByTitre(String titre, Pageable pageable);

    // Filtrage multi-critères (date, lieu, terminée ou non)
    Page<Evenement> filter(LocalDate date, String lieu, Boolean terminee, Pageable pageable);

    // Trier
    Page<Evenement> findAllByOrderByTitreAsc(Pageable pageable);
    Page<Evenement> findAllByOrderByTitreDesc(Pageable pageable);
    Page<Evenement> findAllByOrderByDateAsc(Pageable pageable);
    Page<Evenement> findAllByOrderByDateDesc(Pageable pageable);

    // Participants
    Set<Membre> findParticipantsByEvenementId(Long evenementId);
    long countParticipantsByEvenementId(Long evenementId);

    // Statistiques
    long countTotal();
    long countByTerminee(boolean terminee);
}
