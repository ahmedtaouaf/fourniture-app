package com.app.fourniture.Service;

import com.app.fourniture.Entity.EtatMouvement;
import com.app.fourniture.Repository.EtatMouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtatMouvementService {

    @Autowired
    private EtatMouvementRepository etatMouvementRepository;

    public List<EtatMouvement> findAll() {
        return etatMouvementRepository.findAll();
    }

    public EtatMouvement findById(Long id) {
        return etatMouvementRepository.findById(id).orElse(null);
    }

    public EtatMouvement save(EtatMouvement etatMouvement) {
        return etatMouvementRepository.save(etatMouvement);
    }

    public void deleteById(Long id) {
        etatMouvementRepository.deleteById(id);
    }
}

