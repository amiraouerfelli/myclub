package tn.amiraislem.myclub.service.impl;

import org.springframework.stereotype.Service;
import tn.amiraislem.myclub.entity.Parametres;
import tn.amiraislem.myclub.repository.ParametresRepository;
import tn.amiraislem.myclub.service.ParametresService;

import java.util.List;

@Service
public class ParametresServiceImpl implements ParametresService {

    private final ParametresRepository parametresRepository;

    public ParametresServiceImpl(ParametresRepository parametresRepository) {
        this.parametresRepository = parametresRepository;
    }

    @Override
    public Parametres save(Parametres parametres) {
        return parametresRepository.save(parametres);
    }

    @Override
    public Parametres update(Parametres parametres) {
        return parametresRepository.save(parametres);
    }

    @Override
    public void deleteById(Long id) {
        parametresRepository.deleteById(id);
    }

    @Override
    public Parametres findById(Long id) {
        return parametresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paramètres non trouvés avec l'id : " + id));
    }

    @Override
    public List<Parametres> findAll() {
        return parametresRepository.findAll();
    }
}
