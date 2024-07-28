package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String listeproduits(Model model){

        List<Produit> produitList = produitService.afficherProduits();

        model.addAttribute("produitList",produitList);
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

}
