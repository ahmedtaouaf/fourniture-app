package com.app.fourniture.Repository;

import com.app.fourniture.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query("select count (p) from Produit p")
    int totalproduits();

}
