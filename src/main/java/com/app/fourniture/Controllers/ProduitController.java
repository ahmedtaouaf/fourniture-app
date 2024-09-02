package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/produit-liste")
    public String listeproduits(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Produit> produitPage = produitService.afficherProduits(PageRequest.of(page, 10));
        model.addAttribute("produitList", produitPage.getContent());
        model.addAttribute("totalPages", produitPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "listeproduits";
    }


    @GetMapping("/produit-ajout")
    public String ajouterproduits(Model model){
        model.addAttribute("produit", new Produit());

        return "ajoutproduits";
    }

    @PostMapping("/addproduit")
    public String addMedication(@ModelAttribute Produit produit, RedirectAttributes redirectAttributes) {

        produitService.ajouterProduit(produit);
        redirectAttributes.addFlashAttribute("produitajouter", "Produit ajouté avec succès au stock !");
        return "redirect:/produit-ajout";
    }

    @PostMapping("/produit-modifier")
    public String modifierQuantite(
            @RequestParam("produitId") Long produitId,
            @RequestParam("quantite") Integer quantite,
            @RequestParam("action") String action, RedirectAttributes redirectAttributes) {

        Produit produit = produitService.trouverProduitParId(produitId);
        if (produit != null) {
            int nouvelleQuantite;

            if ("augmenter".equals(action)) {
                nouvelleQuantite = produit.getQuantite() + quantite;
                redirectAttributes.addFlashAttribute("quantiteAugmenter", "Quantité du produit est bien Augmenter !");
            } else if ("reduire".equals(action)) {
                nouvelleQuantite = produit.getQuantite() - quantite;
                if (nouvelleQuantite < 0) {
                    nouvelleQuantite = 0;
                }
                redirectAttributes.addFlashAttribute("quantiteReduit", "Quantité du produit est bien Réduit !");
            } else {
                return "redirect:/produit-liste";
            }

            produit.setQuantite(nouvelleQuantite);
            produitService.save(produit);
        }

        return "redirect:/produit-liste";
    }







}
