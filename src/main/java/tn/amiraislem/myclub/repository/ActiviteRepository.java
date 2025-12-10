package tn.amiraislem.myclub.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Activite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Membre;

import java.time.LocalDate;
import java.util.Set;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    // Nombre total d'activités
    long count();

    // Recherche par titre avec pagination
    Page<Activite> findByTitreContainingIgnoreCase(String titre, Pageable pageable);

    // Nombre d'activités terminées ou non
    long countByTerminee(boolean terminee);

    @Query("""
       SELECT a FROM Activite a
       WHERE (:date IS NULL OR a.date = :date)
         AND (:lieu IS NULL OR LOWER(a.lieu) LIKE LOWER(CONCAT('%', :lieu, '%')))
         AND (:terminee IS NULL OR a.terminee = :terminee)
       """)
    Page<Activite> filter(
            @Param("date") LocalDate date,
            @Param("lieu") String lieu,
            @Param("terminee") Boolean terminee,
            Pageable pageable);

    // Trier par titre ou date asc/desc
    Page<Activite> findAllByOrderByTitreAsc(Pageable pageable);
    Page<Activite> findAllByOrderByTitreDesc(Pageable pageable);
    Page<Activite> findAllByOrderByDateAsc(Pageable pageable);
    Page<Activite> findAllByOrderByDateDesc(Pageable pageable);

    // Récupérer tous les participants pour une activité spécifique
    @Query("SELECT a.participantMembres FROM Activite a WHERE a.id = :activiteId")
    Set<Membre> findParticipantsByActiviteId(@Param("activiteId") Long activiteId);

    // Compter le nombre de participants pour une activité
    @Query("SELECT COUNT(m) FROM Activite a JOIN a.participantMembres m WHERE a.id = :activiteId")
    long countParticipantsByActiviteId(@Param("activiteId") Long activiteId);
}
