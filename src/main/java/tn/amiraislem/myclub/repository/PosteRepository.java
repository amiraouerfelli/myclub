package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Poste;

public interface PosteRepository extends JpaRepository<Poste, Long> {

    // --------------------------
    // RECHERCHES
    // --------------------------

    // Recherche par nom (contient, insensible à la casse)
    Page<Poste> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    // --------------------------
    // STATISTIQUES
    // --------------------------

    // Nombre total de postes
    long count();

    // Nombre de membres occupant ce poste
    @Query("SELECT COUNT(m) FROM Poste p JOIN p.membres m WHERE p.id = :posteId")
    long countMembresByPoste(@Param("posteId") Long posteId);

    // --------------------------
    // TRI
    // --------------------------

    Page<Poste> findAllByOrderByNomAsc(Pageable pageable);
    Page<Poste> findAllByOrderByNomDesc(Pageable pageable);

}
