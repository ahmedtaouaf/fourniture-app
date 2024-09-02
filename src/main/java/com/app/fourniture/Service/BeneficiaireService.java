package com.app.fourniture.Service;

import com.app.fourniture.Entity.Beneficiaire;
import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public void ajouterBeneficiaire(Beneficiaire beneficiaire){
        beneficiaireRepository.save(beneficiaire);
    }

    public List<Beneficiaire> afficherBeneficiaires() {

        return beneficiaireRepository.findAll();
    }
    public List<Beneficiaire> findBeneficiairesByLibelleIn(List<String> libelles) {
        List<Beneficiaire> beneficiaires = beneficiaireRepository.findByLibelleIn(libelles);
        System.out.println("Beneficiaires matching libelles: " + libelles + " are " + beneficiaires);  // Log the result
        return beneficiaires;
    }

    public List<Beneficiaire> findBeneficiairesByste(Boolean iste) {

        List<Beneficiaire> beneficiaires = beneficiaireRepository.findByiste(iste);
        return beneficiaires;
    }

    public Integer totalBeneficiaire() {

        return beneficiaireRepository.totalbeneficaire();
    }
    public List<Beneficiaire> listedivisions() {

        return beneficiaireRepository.listedivisions();
    }

    public Beneficiaire findById(Long id) {
        return beneficiaireRepository.findById(id).orElse(null);
    }

}
