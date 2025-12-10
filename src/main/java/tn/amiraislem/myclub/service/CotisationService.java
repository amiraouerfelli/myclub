package tn.amiraislem.myclub.service;

import tn.amiraislem.myclub.entity.Cotisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CotisationService {

    // CRUD
    Cotisation save(Cotisation cotisation);
    Cotisation update(Cotisation cotisation);
    void deleteById(Long id);
    Cotisation findById(Long id);

    // Recherche simple
    Page<Cotisation> findByMembreId(Long membreId, Pageable pageable);
    Page<Cotisation> findByAnnee(Integer annee, Pageable pageable);
    Page<Cotisation> findByPayee(boolean payee, Pageable pageable);

    // Recherche multi-critères
    Page<Cotisation> filter(Long membreId, Integer annee, Boolean payee, Pageable pageable);

    // Statistiques
    long countTotal();
    long countByPayee(boolean payee);
    Double totalMontantPayee();
}
