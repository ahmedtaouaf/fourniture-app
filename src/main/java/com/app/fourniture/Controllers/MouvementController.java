package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Beneficiaire;
import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Service.BeneficiaireService;
import com.app.fourniture.Service.EtatMouvementService;
import com.app.fourniture.Service.MouvementService;
import com.app.fourniture.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        if (mouvement.getEtatMouvement().getLibelle().equalsIgnoreCase("SORTIE")) {
            Produit produit = mouvement.getProduit();
            if (mouvement.getQuantiteMvn() > produit.getQuantite()) {
                redirectAttributes.addFlashAttribute("stockError", "La quantité du mouvement dépasse la quantité disponible du produit.");
                return "redirect:/mouvement/new";
            }
            produit.setQuantite(produit.getQuantite() - mouvement.getQuantiteMvn());
            produitService.save(produit);
            redirectAttributes.addFlashAttribute("mouvementajouter", "Mouvement Sortie ajouté avec succès.");
        }
        else if (mouvement.getEtatMouvement().getLibelle().equalsIgnoreCase("ENTREE")) {
            Produit produit = mouvement.getProduit();

            produit.setQuantite(produit.getQuantite() + mouvement.getQuantiteMvn());
            produitService.save(produit);
            redirectAttributes.addFlashAttribute("mouvemententree", "Mouvement Entree bien enregistrer.");
        }
        else if (mouvement.getEtatMouvement().getLibelle().equalsIgnoreCase("EN COURS")) {
            Produit produit = mouvement.getProduit();

            produitService.save(produit);
            redirectAttributes.addFlashAttribute("mouvementencours", "Mouvement En cours bien enregistrer.");
        }

        else if (mouvement.getEtatMouvement().getLibelle().equalsIgnoreCase("AVANCE")) {
            Produit produit = mouvement.getProduit();
            if (mouvement.getQuantiteMvn() > produit.getQuantite()) {
                redirectAttributes.addFlashAttribute("stockError", "La quantité du mouvement dépasse la quantité disponible du produit.");
                return "redirect:/mouvement/new";
            }
            produit.setQuantite(produit.getQuantite() - mouvement.getQuantiteMvn());
            produitService.save(produit);
            redirectAttributes.addFlashAttribute("mouvementavance", "Mouvement Avance ajouté avec succès.");
        }

        mouvementService.save(mouvement);


        return "redirect:/mouvement/new";
    }

    @GetMapping("/beneficiaires/filter")
    @ResponseBody
    public List<Beneficiaire> getFilteredBeneficiaires(@RequestParam("etatMouvement") String etatMouvement) {


        /*if (etatMouvement.equalsIgnoreCase("ENTREE")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByLibelleIn(Arrays.asList("CABINET", "DINFO", "DRH"));
            System.out.println("Filtered Beneficiaires for ENTREE: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        } else if (etatMouvement.equalsIgnoreCase("EN COURS")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByLibelleIn(Arrays.asList("CABINET", "DII"));
            System.out.println("Filtered Beneficiaires for EN COURS: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        } else {
            List<Beneficiaire> allBeneficiaires = beneficiaireService.afficherBeneficiaires();
            System.out.println("All Beneficiaires: " + allBeneficiaires);
            return allBeneficiaires;
        }*/

        if (etatMouvement.equalsIgnoreCase("ENTREE")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByste(true);
            System.out.println("Filtered Beneficiaires for ENTREE: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        } else if (etatMouvement.equalsIgnoreCase("EN COURS")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByste(false);
            System.out.println("Filtered Beneficiaires for EN COURS: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        }
        else if (etatMouvement.equalsIgnoreCase("SORTIE")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByste(false);
            System.out.println("Filtered Beneficiaires for EN COURS: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        }
        else if (etatMouvement.equalsIgnoreCase("AVANCE")) {
            List<Beneficiaire> filteredBeneficiaires = beneficiaireService.findBeneficiairesByste(false);
            System.out.println("Filtered Beneficiaires for EN COURS: " + filteredBeneficiaires);
            return filteredBeneficiaires;
        }
        else {
            List<Beneficiaire> allBeneficiaires = beneficiaireService.afficherBeneficiaires();
            System.out.println("All Beneficiaires: " + allBeneficiaires);
            return allBeneficiaires;
        }


    }




    @GetMapping("/mouvement/liste")
    public String listeMouvements(Model model){

        model.addAttribute("mouvements", mouvementService.findAll());
        return "mouvementListe";
    }




}
