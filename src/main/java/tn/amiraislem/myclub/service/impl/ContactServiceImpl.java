package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Contact;
import tn.amiraislem.myclub.repository.ContactRepository;
import tn.amiraislem.myclub.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    // Injection via constructeur
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // ---------------- CRU D ----------------
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact); // save gère insert & update
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact non trouvé avec id : " + id));
    }

    // ---------------- RECHERCHE SIMPLE ----------------
    @Override
    public Page<Contact> findByNom(String nom, Pageable pageable) {
        return contactRepository.findByNomContainingIgnoreCase(nom, pageable);
    }

    @Override
    public Page<Contact> findByOrganisation(String organisation, Pageable pageable) {
        return contactRepository.findByOrganisationContainingIgnoreCase(organisation, pageable);
    }

    @Override
    public Page<Contact> findByRole(String role, Pageable pageable) {
        return contactRepository.findByRoleContainingIgnoreCase(role, pageable);
    }

    @Override
    public Page<Contact> findByEmail(String email, Pageable pageable) {
        return contactRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    // ---------------- RECHERCHE MULTI-CRITÈRES ----------------
    @Override
    public Page<Contact> filter(String nom, String organisation, String role, Pageable pageable) {
        return contactRepository.filter(nom, organisation, role, pageable);
    }

    // ---------------- STATISTIQUES ----------------
    @Override
    public long countTotal() {
        return contactRepository.count();
    }

    @Override
    public long countByRole(String role) {
        return contactRepository.countByRole(role);
    }
}
