package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.repository.EvenementRepository;
import tn.amiraislem.myclub.service.EvenementService;
import java.time.LocalDate;
import java.util.Set;

@Service
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    // ---------------- CRUD ----------------
    @Override
    public Evenement save(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement update(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public void deleteById(Long id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public Evenement findById(Long id) {
        return evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé avec id : " + id));
    }

    // ---------------- RECHERCHE ----------------
    @Override
    public Page<Evenement> findByTitre(String titre, Pageable pageable) {
        return evenementRepository.findByTitreContainingIgnoreCase(titre, pageable);
    }

    @Override
    public Page<Evenement> filter(LocalDate date, String lieu, Boolean terminee, Pageable pageable) {
        return evenementRepository.filter(date, lieu, terminee, pageable);
    }

    // ---------------- TRI ----------------
    @Override
    public Page<Evenement> findAllByOrderByTitreAsc(Pageable pageable) {
        return evenementRepository.findAllByOrderByTitreAsc(pageable);
    }

    @Override
    public Page<Evenement> findAllByOrderByTitreDesc(Pageable pageable) {
        return evenementRepository.findAllByOrderByTitreDesc(pageable);
    }

    @Override
    public Page<Evenement> findAllByOrderByDateAsc(Pageable pageable) {
        return evenementRepository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Evenement> findAllByOrderByDateDesc(Pageable pageable) {
        return evenementRepository.findAllByOrderByDateDesc(pageable);
    }

    // ---------------- PARTICIPANTS ----------------
    @Override
    public Set<Membre> findParticipantsByEvenementId(Long evenementId) {
        return evenementRepository.findParticipantsByEvenementId(evenementId);
    }

    @Override
    public long countParticipantsByEvenementId(Long evenementId) {
        return evenementRepository.countParticipantsByEvenementId(evenementId);
    }

    // ---------------- STATISTIQUES ----------------
    @Override
    public long countTotal() {
        return evenementRepository.count();
    }

    @Override
    public long countByTerminee(boolean terminee) {
        return evenementRepository.countByTerminee(terminee);
    }
}
