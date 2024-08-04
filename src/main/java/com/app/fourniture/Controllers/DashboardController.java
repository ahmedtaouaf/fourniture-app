package com.app.fourniture.Controllers;

import com.app.fourniture.Repository.MouvementRepository;
import com.app.fourniture.Repository.ProduitRepository;
import com.app.fourniture.Service.BeneficiaireService;
import com.app.fourniture.Service.MouvementService;
import com.app.fourniture.Service.ProduitService;
import com.itextpdf.text.pdf.qrcode.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private BeneficiaireService beneficiaireService;
    @Autowired
    private MouvementService mouvementService;

    @GetMapping("/")
    public String mainpage(Model model){

        model.addAttribute("totalProduits", produitService.totalProduits());
        model.addAttribute("totalBeneficiaire", beneficiaireService.totalBeneficiaire());
        model.addAttribute("totalMvn", mouvementService.totalMvns());
        return "index";
    }
}
