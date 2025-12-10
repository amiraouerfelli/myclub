package tn.amiraislem.myclub.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.amiraislem.myclub.entity.Evenement;
import tn.amiraislem.myclub.entity.Membre;
import tn.amiraislem.myclub.repository.MembreRepository;
import tn.amiraislem.myclub.service.MembreService;

import java.util.Optional;

@Service
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;

    public MembreServiceImpl(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @Override
    public Membre createMembre(Membre membre) {
        // Email unique
        membreRepository.findByEmail(membre.getEmail()).ifPresent(m -> {
            throw new RuntimeException("Email déjà utilisé !");
        });

        // Téléphone unique
        membreRepository.findByTelephone(membre.getTelephone()).ifPresent(m -> {
            throw new RuntimeException("Téléphone déjà utilisé !");
        });

        return membreRepository.save(membre);
    }

    @Override
    public Membre updateMembre(Long id, Membre membre) {
        Membre existing = membreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre introuvable"));

        existing.setNom(membre.getNom());
        existing.setPrenom(membre.getPrenom());
        existing.setEmail(membre.getEmail());
        existing.setTelephone(membre.getTelephone());
        existing.setDateAdhesion(membre.getDateAdhesion());
        existing.setDepartement(membre.getDepartement());
        existing.setPoste(membre.getPoste());

        return membreRepository.save(existing);
    }

    @Override
    public void deleteMembre(Long id) {
        membreRepository.deleteById(id);
    }

    @Override
    public Membre getMembreById(Long id) {
        return membreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre introuvable"));
    }

    @Override
    public Page<Membre> getAllMembres(Pageable pageable) {
        return membreRepository.findAll(pageable);
    }

    @Override
    public Page<Membre> search(String keyword, Pageable pageable) {
        return membreRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelephoneContainingIgnoreCase(
                        keyword, keyword, keyword, keyword, pageable
                );
    }

    @Override
    public Page<Membre> getMembresByPoste(Long posteId, Pageable pageable) {
        return membreRepository.findByPosteId(posteId, pageable);
    }

    @Override
    public Page<Membre> getMembresByDepartement(Long departementId, Pageable pageable) {
        return membreRepository.findByDepartementId(departementId, pageable);
    }

    @Override
    public Page<Membre> sortBy(String field, String direction, Pageable pageable) {
        switch (field.toLowerCase()) {
            case "nom":
                return direction.equalsIgnoreCase("asc")
                        ? membreRepository.findAllByOrderByNomAsc(pageable)
                        : membreRepository.findAllByOrderByNomDesc(pageable);

            case "prenom":
                return direction.equalsIgnoreCase("asc")
                        ? membreRepository.findAllByOrderByPrenomAsc(pageable)
                        : membreRepository.findAllByOrderByPrenomDesc(pageable);

            case "date":
                return direction.equalsIgnoreCase("asc")
                        ? membreRepository.findAllByOrderByDateAdhesionAsc(pageable)
                        : membreRepository.findAllByOrderByDateAdhesionDesc(pageable);

            default:
                return membreRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Evenement> getEvenementsOfMembre(Long membreId, Pageable pageable) {
        return membreRepository.findEvenementsByMembre(membreId, pageable);
    }

    @Override
    public long countAll() {
        return membreRepository.count();
    }

    @Override
    public long countByDepartement(Long departementId) {
        return membreRepository.countByDepartementId(departementId);
    }

    @Override
    public Optional<Membre> findByEmail(String email) {
        return membreRepository.findByEmail(email);
    }

    @Override
    public Optional<Membre> findByTelephone(String telephone) {
        return membreRepository.findByTelephone(telephone);
    }
}
