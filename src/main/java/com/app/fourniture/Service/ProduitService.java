package com.app.fourniture.Service;

import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Produit> afficherProduits(Pageable pageable) {
        return produitRepository.findAll(pageable);
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

    public Produit trouverProduitParId(Long id) {
        return produitRepository.findById(id).orElse(null);
    }
}
