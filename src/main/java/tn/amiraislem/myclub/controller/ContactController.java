package tn.amiraislem.myclub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.amiraislem.myclub.entity.Contact;
import tn.amiraislem.myclub.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // -------------------- CREATE --------------------
    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return contactService.save(contact);
    }

    // -------------------- UPDATE --------------------
    @PutMapping("/{id}")
    public Contact update(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id); // important
        return contactService.update(contact);
    }

    // -------------------- DELETE --------------------
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.deleteById(id);
    }

    // -------------------- FIND BY ID --------------------
    @GetMapping("/{id}")
    public Contact getById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    // -------------------- RECHERCHE SIMPLE --------------------
    @GetMapping("/search/nom")
    public Page<Contact> searchByNom(
            @RequestParam String nom,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return contactService.findByNom(nom, PageRequest.of(page, size));
    }

    @GetMapping("/search/organisation")
    public Page<Contact> searchByOrganisation(
            @RequestParam String organisation,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return contactService.findByOrganisation(organisation, PageRequest.of(page, size));
    }

    @GetMapping("/search/role")
    public Page<Contact> searchByRole(
            @RequestParam String role,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return contactService.findByRole(role, PageRequest.of(page, size));
    }

    @GetMapping("/search/email")
    public Page<Contact> searchByEmail(
            @RequestParam String email,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return contactService.findByEmail(email, PageRequest.of(page, size));
    }

    // -------------------- RECHERCHE MULTI-CRITÈRES --------------------
    @GetMapping("/filter")
    public Page<Contact> filter(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String organisation,
            @RequestParam(required = false) String role,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return contactService.filter(nom, organisation, role, PageRequest.of(page, size));
    }

    // -------------------- STATS --------------------
    @GetMapping("/stats/total")
    public long getTotal() {
        return contactService.countTotal();
    }

    @GetMapping("/stats/role")
    public long getCountByRole(@RequestParam String role) {
        return contactService.countByRole(role);
    }
}
