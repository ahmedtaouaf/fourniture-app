package com.app.fourniture.Service;

import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Repository.MouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MouvementService {

    @Autowired
    private MouvementRepository mouvementRepository;

    public List<Mouvement> findAll() {
        return mouvementRepository.findAll();
    }

    public Mouvement findById(Long id) {
        return mouvementRepository.findById(id).orElse(null);
    }

    public Mouvement save(Mouvement mouvement) {
        return mouvementRepository.save(mouvement);
    }

    public void deleteById(Long id) {
        mouvementRepository.deleteById(id);
    }
}
