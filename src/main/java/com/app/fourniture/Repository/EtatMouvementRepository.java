package com.app.fourniture.Repository;

import com.app.fourniture.Entity.EtatMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatMouvementRepository extends JpaRepository<EtatMouvement, Long> {
}

