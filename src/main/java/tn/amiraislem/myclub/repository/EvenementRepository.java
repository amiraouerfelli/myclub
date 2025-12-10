package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;

import java.time.LocalDate;
import java.util.Set;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    // Nombre total d'événements
    long count();

    // Recherche par titre avec pagination
    Page<Evenement> findByTitreContainingIgnoreCase(String titre, Pageable pageable);

    // Nombre d'événements terminés ou non
    long countByTerminee(boolean terminee);

    // Filtrage multi-critères : date, lieu, état
    @Query("""
       SELECT e FROM Evenement e
       WHERE (:date IS NULL OR e.date = :date)
         AND (:lieu IS NULL OR LOWER(e.lieu) LIKE LOWER(CONCAT('%', :lieu, '%')))
         AND (:terminee IS NULL OR e.terminee = :terminee)
       """)
    Page<Evenement> filter(
            @Param("date") LocalDate date,
            @Param("lieu") String lieu,
            @Param("terminee") Boolean terminee,
            Pageable pageable);

    // Trier par titre ou date asc/desc
    Page<Evenement> findAllByOrderByTitreAsc(Pageable pageable);
    Page<Evenement> findAllByOrderByTitreDesc(Pageable pageable);
    Page<Evenement> findAllByOrderByDateAsc(Pageable pageable);
    Page<Evenement> findAllByOrderByDateDesc(Pageable pageable);

    // Récupérer tous les participants pour un événement spécifique
    @Query("SELECT e.participantMembres FROM Evenement e WHERE e.id = :evenementId")
    Set<Membre> findParticipantsByEvenementId(@Param("evenementId") Long evenementId);

    // Compter le nombre de participants pour un événement
    @Query("SELECT COUNT(m) FROM Evenement e JOIN e.participantMembres m WHERE e.id = :evenementId")
    long countParticipantsByEvenementId(@Param("evenementId") Long evenementId);
}
