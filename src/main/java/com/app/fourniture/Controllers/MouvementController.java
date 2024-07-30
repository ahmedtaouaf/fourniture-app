package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Service.BeneficiaireService;
import com.app.fourniture.Service.EtatMouvementService;
import com.app.fourniture.Service.MouvementService;
import com.app.fourniture.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class MouvementController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private BeneficiaireService beneficiaireService;

    @Autowired
    private EtatMouvementService etatMouvementService;

    @Autowired
    private MouvementService mouvementService;

    @GetMapping("/mouvement/new")
    public String showMouvementForm(Model model) {
        model.addAttribute("mouvement", new Mouvement());
        model.addAttribute("produits", produitService.afficherProduits());
        model.addAttribute("beneficiaires", beneficiaireService.afficherBeneficiaires());
        model.addAttribute("etatMouvements", etatMouvementService.findAll());
        return "mouvementForm";
    }

    @PostMapping("/mouvement/save")
    public String saveMouvement(@ModelAttribute("mouvement") Mouvement mouvement, RedirectAttributes redirectAttributes) {
        // Update product quantity if EtatMouvement is "Sortie"
        if (mouvement.getEtatMouvement().getLibelle().equalsIgnoreCase("Sortie")) {
            Produit produit = mouvement.getProduit();
            produit.setQuantite(produit.getQuantite() - mouvement.getQuantiteMvn());
            produitService.save(produit);
        }

        mouvementService.save(mouvement);
        redirectAttributes.addFlashAttribute("mouvementajouter", "Mouvement ajouté avec succès.");

        return "redirect:/mouvement/new";
    }


}
