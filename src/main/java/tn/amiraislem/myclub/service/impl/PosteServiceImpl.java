package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import tn.amiraislem.myclub.entity.Poste;
import tn.amiraislem.myclub.repository.PosteRepository;
import tn.amiraislem.myclub.service.PosteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class PosteServiceImpl implements PosteService {

    private final PosteRepository posteRepository;

    public PosteServiceImpl(PosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    @Override
    public Poste save(Poste poste) {
        return posteRepository.save(poste);
    }

    @Override
    public Poste update(Poste poste) {
        return posteRepository.save(poste);
    }

    @Override
    public void deleteById(Long id) {
        posteRepository.deleteById(id);
    }

    @Override
    public Poste findById(Long id) {
        return posteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poste non trouvé avec l'id : " + id));
    }

    @Override
    public Page<Poste> findByNom(String nom, Pageable pageable) {
        return posteRepository.findByNomContainingIgnoreCase(nom, pageable);
    }

    @Override
    public long count() {
        return posteRepository.count();
    }

    @Override
    public long countMembresByPoste(Long posteId) {
        return posteRepository.countMembresByPoste(posteId);
    }

    @Override
    public Page<Poste> findAllOrderByNomAsc(Pageable pageable) {
        return posteRepository.findAllByOrderByNomAsc(pageable);
    }

    @Override
    public Page<Poste> findAllOrderByNomDesc(Pageable pageable) {
        return posteRepository.findAllByOrderByNomDesc(pageable);
    }

    @Override
    public List<Poste> findAll() {
        return posteRepository.findAll();
    }
}
