package com.app.fourniture.Repository;

import com.app.fourniture.Entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {

    List<Beneficiaire> findByLibelleIn(List<String> libelles);

    List<Beneficiaire> findByiste(Boolean iste);

    @Query("select count (b) from Beneficiaire b")
    int totalbeneficaire();

    @Query("SELECT b FROM Beneficiaire b WHERE b.iste is false ")
    List<Beneficiaire> listedivisions();

}
