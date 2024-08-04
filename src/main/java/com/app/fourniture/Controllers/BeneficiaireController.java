package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Beneficiaire;
import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Entity.Produit;
import com.app.fourniture.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;

    @GetMapping("/beneficiaires-liste")
    public String benefListe(Model model){
        List<Beneficiaire> beneficiairesList = beneficiaireService.afficherBeneficiaires();

        model.addAttribute("beneficiairesList",beneficiairesList);
        return "listebeneficiaires";
    }
    @GetMapping("/beneficiaires-ajout")
    public String benefAjout(Model model){

        model.addAttribute("beneficiaire", new Beneficiaire());
        return "ajoutbeneficiaire";
    }
    @PostMapping("/addbeneficiaire")
    public String addBeneficiaire(@ModelAttribute Beneficiaire beneficiaire, RedirectAttributes redirectAttributes) {

        beneficiaireService.ajouterBeneficiaire(beneficiaire);
        redirectAttributes.addFlashAttribute("beneficiaireajouter", "Bénéficiaire ajouté avec succès !");
        return "redirect:/beneficiaires-ajout";
    }

}
