package tn.amiraislem.myclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.amiraislem.myclub.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    // ------------------------------------
    // RECHERCHES SIMPLES
    // ------------------------------------
    Page<Contact> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    Page<Contact> findByOrganisationContainingIgnoreCase(String organisation, Pageable pageable);

    Page<Contact> findByRoleContainingIgnoreCase(String role, Pageable pageable);

    Page<Contact> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    // ------------------------------------
    // RECHERCHE MULTI-CRITERES (nom + organisation + role)
    // ------------------------------------
    @Query("""
        SELECT c FROM Contact c
        WHERE (:nom IS NULL OR LOWER(c.nom) LIKE LOWER(CONCAT('%', :nom, '%')))
          AND (:organisation IS NULL OR LOWER(c.organisation) LIKE LOWER(CONCAT('%', :organisation, '%')))
          AND (:role IS NULL OR LOWER(c.role) LIKE LOWER(CONCAT('%', :role, '%')))
    """)
    Page<Contact> filter(
            @Param("nom") String nom,
            @Param("organisation") String organisation,
            @Param("role") String role,
            Pageable pageable
    );

    // ------------------------------------
    // STATISTIQUES
    // ------------------------------------
    long count(); // total contacts

    long countByRole(String role); // ex: nombre de SPEAKERS ou SPONSORS
}
