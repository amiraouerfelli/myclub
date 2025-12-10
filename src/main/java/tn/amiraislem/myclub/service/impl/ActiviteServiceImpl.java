package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Activite;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.repository.ActiviteRepository;
import tn.amiraislem.myclub.service.ActiviteService;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ActiviteServiceImpl implements ActiviteService {

    private final ActiviteRepository activiteRepository;

    // Injection via constructeur
    public ActiviteServiceImpl(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    // ---------------- CRU D ----------------
    @Override
    public Activite save(Activite activite) {
        return activiteRepository.save(activite);
    }

    @Override
    public Activite update(Activite activite) {
        return activiteRepository.save(activite); // save gère insert & update
    }

    @Override
    public void deleteById(Long id) {
        activiteRepository.deleteById(id);
    }

    @Override
    public Activite findById(Long id) {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée avec id : " + id));
    }

    // ---------------- RECHERCHE & FILTRES ----------------
    @Override
    public Page<Activite> findByTitre(String titre, Pageable pageable) {
        return activiteRepository.findByTitreContainingIgnoreCase(titre, pageable);
    }

    @Override
    public Page<Activite> filter(LocalDate date, String lieu, Boolean terminee, Pageable pageable) {
        return activiteRepository.filter(date, lieu, terminee, pageable);
    }

    // ---------------- TRI ----------------
    @Override
    public Page<Activite> findAllOrderByTitreAsc(Pageable pageable) {
        return activiteRepository.findAllByOrderByTitreAsc(pageable);
    }

    @Override
    public Page<Activite> findAllOrderByTitreDesc(Pageable pageable) {
        return activiteRepository.findAllByOrderByTitreDesc(pageable);
    }

    @Override
    public Page<Activite> findAllOrderByDateAsc(Pageable pageable) {
        return activiteRepository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Activite> findAllOrderByDateDesc(Pageable pageable) {
        return activiteRepository.findAllByOrderByDateDesc(pageable);
    }

    // ---------------- PARTICIPANTS ----------------
    @Override
    public Set<Membre> findParticipants(Long activiteId) {
        return activiteRepository.findParticipantsByActiviteId(activiteId);
    }

    @Override
    public long countParticipants(Long activiteId) {
        return activiteRepository.countParticipantsByActiviteId(activiteId);
    }

    // ---------------- STATISTIQUES ----------------
    @Override
    public long countTotal() {
        return activiteRepository.count();
    }

    @Override
    public long countByTerminee(boolean terminee) {
        return activiteRepository.countByTerminee(terminee);
    }
}
