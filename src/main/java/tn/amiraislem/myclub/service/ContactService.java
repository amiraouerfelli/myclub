package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    // CRUD
    Contact save(Contact contact);
    Contact update(Contact contact);
    void deleteById(Long id);
    Contact findById(Long id);

    // Recherche simple
    Page<Contact> findByNom(String nom, Pageable pageable);
    Page<Contact> findByOrganisation(String organisation, Pageable pageable);
    Page<Contact> findByRole(String role, Pageable pageable);
    Page<Contact> findByEmail(String email, Pageable pageable);

    // Recherche multi-critères
    Page<Contact> filter(String nom, String organisation, String role, Pageable pageable);

    // Statistiques
    long countTotal();
    long countByRole(String role);
}
