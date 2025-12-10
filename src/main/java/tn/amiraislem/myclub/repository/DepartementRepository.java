package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    // --------------------------
    // RECHERCHES
    // --------------------------

    // Recherche par nom (contient, insensible à la casse)
    Page<Departement> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    // --------------------------
    // STATISTIQUES
    // --------------------------

    // Nombre total de départements
    long count();

    // Nombre de membres dans un département spécifique
    @Query("SELECT COUNT(m) FROM Departement d JOIN d.membres m WHERE d.id = :departementId")
    long countMembresByDepartement(@Param("departementId") Long departementId);

    // --------------------------
    // TRI
    // --------------------------

    Page<Departement> findAllByOrderByNomAsc(Pageable pageable);
    Page<Departement> findAllByOrderByNomDesc(Pageable pageable);

}
