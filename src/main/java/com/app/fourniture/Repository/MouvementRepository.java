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

    @Query("SELECT e.libelle, COUNT(m) FROM Mouvement m JOIN m.etatMouvement e GROUP BY e.libelle")
    List<Object[]> countMouvementsByEtatMouvement();

    @Query(value = "SELECT DATE_FORMAT(date, '%Y-%m') AS month, COUNT(*) FROM Mouvement GROUP BY DATE_FORMAT(date, '%Y-%m')", nativeQuery = true)
    List<Object[]> countMouvementsPerMonth();

    @Query(value = "SELECT p.libelle AS produit_libelle, m.date, b.libelle AS beneficiaire_libelle, m.quantite_mvn, e.libelle AS etatmouvement_libelle FROM mouvement m JOIN Produit p ON m.produit_id = p.id JOIN Beneficiaire b ON m.beneficiaire_id = b.id JOIN etat_mouvement e ON m.etatmouvement_id = e.id ORDER BY m.date DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findLast10Mouvements();





}
