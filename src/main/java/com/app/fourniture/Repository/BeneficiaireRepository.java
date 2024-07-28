package com.app.fourniture.Repository;

import com.app.fourniture.Entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
}
