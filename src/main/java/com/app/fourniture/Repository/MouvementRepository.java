package com.app.fourniture.Repository;

import com.app.fourniture.Entity.Beneficiaire;
import com.app.fourniture.Entity.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    @Query("select count (m) from Mouvement m")
    int totalMvn();


    @Query("""
    SELECT m
    FROM Mouvement m
    LEFT JOIN Produit p ON p.id = m.produit.id
    LEFT JOIN EtatMouvement e ON e.id = m.etatMouvement.id
    LEFT JOIN Beneficiaire b ON b.id = m.beneficiaire.id
    WHERE m.beneficiaire.id = :beneficiaire
    AND m.etatMouvement.id=4
    """)
    List<Mouvement> beneficiairebyetatavance(@Param("beneficiaire") Long beneficiaire);


}
