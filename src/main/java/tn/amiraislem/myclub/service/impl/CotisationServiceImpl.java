package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Cotisation;
import tn.amiraislem.myclub.repository.CotisationRepository;
import tn.amiraislem.myclub.service.CotisationService;

@Service
public class CotisationServiceImpl implements CotisationService {

    private final CotisationRepository cotisationRepository;

    // Injection via constructeur
    public CotisationServiceImpl(CotisationRepository cotisationRepository) {
        this.cotisationRepository = cotisationRepository;
    }

    // ---------------- CRU D ----------------
    @Override
    public Cotisation save(Cotisation cotisation) {
        return cotisationRepository.save(cotisation);
    }

    @Override
    public Cotisation update(Cotisation cotisation) {
        return cotisationRepository.save(cotisation);
    }

    @Override
    public void deleteById(Long id) {
        cotisationRepository.deleteById(id);
    }

    @Override
    public Cotisation findById(Long id) {
        return cotisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotisation non trouvée avec id : " + id));
    }

    // ---------------- RECHERCHE SIMPLE ----------------
    @Override
    public Page<Cotisation> findByMembreId(Long membreId, Pageable pageable) {
        return cotisationRepository.findByMembreId(membreId, pageable);
    }

    @Override
    public Page<Cotisation> findByAnnee(Integer annee, Pageable pageable) {
        return cotisationRepository.findByAnnee(annee, pageable);
    }

    @Override
    public Page<Cotisation> findByPayee(boolean payee, Pageable pageable) {
        return cotisationRepository.findByPayee(payee, pageable);
    }

    // ---------------- RECHERCHE MULTI-CRITÈRES ----------------
    @Override
    public Page<Cotisation> filter(Long membreId, Integer annee, Boolean payee, Pageable pageable) {
        return cotisationRepository.filter(membreId, annee, payee, pageable);
    }

    // ---------------- STATISTIQUES ----------------
    @Override
    public long countTotal() {
        return cotisationRepository.count();
    }

    @Override
    public long countByPayee(boolean payee) {
        return cotisationRepository.countByPayee(payee);
    }

    @Override
    public Double totalMontantPayee() {
        return cotisationRepository.totalMontantPayee();
    }
}
