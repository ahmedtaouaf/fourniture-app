package com.app.fourniture.Service;

import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public void ajouterProduit(Produit produit){
        produitRepository.save(produit);
    }

    public List<Produit> afficherProduits() {

        return produitRepository.findAll();
    }

    public Integer totalProduits() {

        return produitRepository.totalproduits();
    }

    public void supprimerProduit(long id) {

        produitRepository.deleteById(id);
    }
    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }
}
