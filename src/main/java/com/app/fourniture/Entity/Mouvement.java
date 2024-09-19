package com.app.fourniture.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private Integer quantiteMvn;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "beneficiaire_id", nullable = false)
    private Beneficiaire beneficiaire;

    @ManyToOne
    @JoinColumn(name = "etatmouvement_id", nullable = false)
    private EtatMouvement etatMouvement;

    @Transient
    private String formattedDate;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}

