package com.app.fourniture.Repository;

import com.app.fourniture.Entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {

    List<Beneficiaire> findByLibelleIn(List<String> libelles);

}
