package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;

import java.util.Optional;

public interface MembreRepository extends JpaRepository<Membre, Long> {

    // Recherche dans nom, prénom, email, téléphone avec pagination
    Page<Membre> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelephoneContainingIgnoreCase(
            String nom, String prenom, String email, String telephone, Pageable pageable);

    // --------------------------
    //  STATISTIQUES
    // --------------------------

    long count();

    long countByDepartementId(Long departementId);

    // --------------------------
    // 🔗 GESTION DES RELATIONS
    // --------------------------
    @Query("SELECT m.evenements FROM Membre m WHERE m.id = :membreId")
    Page<Evenement> findEvenementsByMembre(@Param("membreId") Long membreId, Pageable pageable);

    // --------------------------
    //  FILTRES
    // --------------------------
    Page<Membre> findByPosteId(Long posteId, Pageable pageable);
    Page<Membre> findByDepartementId(Long departementId, Pageable pageable);

    // --------------------------
    //  TRI — grâce à "OrderBy" avec pagination
    // --------------------------
    Page<Membre> findAllByOrderByNomAsc(Pageable pageable);
    Page<Membre> findAllByOrderByNomDesc(Pageable pageable);

    Page<Membre> findAllByOrderByPrenomAsc(Pageable pageable);
    Page<Membre> findAllByOrderByPrenomDesc(Pageable pageable);

    Page<Membre> findAllByOrderByDateAdhesionAsc(Pageable pageable);
    Page<Membre> findAllByOrderByDateAdhesionDesc(Pageable pageable);

    // Email unique → Optional
    Optional<Membre> findByEmail(String email);

    // Chercher par téléphone
    Optional<Membre> findByTelephone(String telephone);
}
