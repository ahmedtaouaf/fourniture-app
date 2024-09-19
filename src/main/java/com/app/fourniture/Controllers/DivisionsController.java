package com.app.fourniture.Controllers;

import com.app.fourniture.Entity.Beneficiaire;
import com.app.fourniture.Entity.Mouvement;
import com.app.fourniture.Service.BeneficiaireService;
import com.app.fourniture.Service.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Controller
public class DivisionsController {


    @Autowired
    private BeneficiaireService beneficiaireService;
    @Autowired
    private MouvementService mouvementService;

    @GetMapping("/divisions/liste")
    public String divisions(Model model){


        model.addAttribute("listedivisions", beneficiaireService.listedivisions());
        return "divisionsListe";
    }



    @GetMapping("/division/{id}")
    public String getBeneficiaireDetails(@PathVariable Long id, Model model) {
        Beneficiaire beneficiaire = beneficiaireService.findById(id);

        List<Mouvement> listBeneficiaireByEtatAvance = mouvementService.beneficiaireByEtatAvance(id);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        listBeneficiaireByEtatAvance.forEach(mouvement -> {
            String formattedDate = mouvement.getDate().format(formatter);
            mouvement.setFormattedDate(formattedDate);
        });

        model.addAttribute("beneficiaire", beneficiaire);
        model.addAttribute("mouvements", listBeneficiaireByEtatAvance);
        return "divisionsDetails";
    }



}
