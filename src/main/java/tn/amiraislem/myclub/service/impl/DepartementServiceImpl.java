package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.amiraislem.myclub.entity.Departement;
import tn.amiraislem.myclub.repository.DepartementRepository;
import tn.amiraislem.myclub.service.DepartementService;

@Service
public class DepartementServiceImpl implements DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    // ---------------- CRU D ----------------
    @Override
    public Departement save(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement update(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public void deleteById(Long id) {
        departementRepository.deleteById(id);
    }

    @Override
    public Departement findById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé avec id : " + id));
    }

    // ---------------- RECHERCHE ----------------
    @Override
    public Page<Departement> findByNom(String nom, Pageable pageable) {
        return departementRepository.findByNomContainingIgnoreCase(nom, pageable);
    }

    // ---------------- STATISTIQUES ----------------
    @Override
    public long countTotal() {
        return departementRepository.count();
    }

    @Override
    public long countMembresByDepartement(Long departementId) {
        return departementRepository.countMembresByDepartement(departementId);
    }

    // ---------------- TRI ----------------
    @Override
    public Page<Departement> findAllByOrderByNomAsc(Pageable pageable) {
        return departementRepository.findAllByOrderByNomAsc(pageable);
    }

    @Override
    public Page<Departement> findAllByOrderByNomDesc(Pageable pageable) {
        return departementRepository.findAllByOrderByNomDesc(pageable);
    }
}
