package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Cotisation;

import java.util.List;

public interface CotisationRepository extends JpaRepository<Cotisation, Long> {

    // --------------------------
    // RECHERCHES
    // --------------------------
    Page<Cotisation> findByMembreId(Long membreId, Pageable pageable);

    Page<Cotisation> findByAnnee(Integer annee, Pageable pageable);

    Page<Cotisation> findByPayee(boolean payee, Pageable pageable);

    // Recherche combinée multi-critères (membre + année + payée/non payée)
    @Query("""
        SELECT c FROM Cotisation c
        WHERE (:membreId IS NULL OR c.membre.id = :membreId)
          AND (:annee IS NULL OR c.annee = :annee)
          AND (:payee IS NULL OR c.payee = :payee)
    """)
    Page<Cotisation> filter(
            @Param("membreId") Long membreId,
            @Param("annee") Integer annee,
            @Param("payee") Boolean payee,
            Pageable pageable
    );

    // --------------------------
    // STATISTIQUES
    // --------------------------
    long count(); // total cotisations

    long countByPayee(boolean payee); // nombre cotisations payées ou non

    @Query("SELECT SUM(c.montant) FROM Cotisation c WHERE c.payee = true")
    Double totalMontantPayee();

}
