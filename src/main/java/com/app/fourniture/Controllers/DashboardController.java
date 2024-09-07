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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private BeneficiaireService beneficiaireService;
    @Autowired
    private MouvementService mouvementService;
    @Autowired
    private MouvementRepository mouvementRepository;

    @GetMapping("/")
    public String mainpage(Model model){
        // Fetch data for the bar chart
        List<Object[]> mouvementCounts = mouvementRepository.countMouvementsByEtatMouvement();

        List<String> labels = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        List<Object[]> recentMouvements = mouvementRepository.findLast10Mouvements();


        for (Object[] result : mouvementCounts) {
            labels.add((String) result[0]);
            Object count = result[1];
            if (count instanceof BigInteger) {
                counts.add(((BigInteger) count).longValue()); // Convert BigInteger to Long
            } else if (count instanceof Long) {
                counts.add((Long) count); // Directly add Long
            }
        }

        // Fetch data for the monthly chart
        List<Object[]> monthlyMouvements = mouvementRepository.countMouvementsPerMonth();
        List<String> months = new ArrayList<>();
        List<Long> monthlyCounts = new ArrayList<>();

        for (Object[] result : monthlyMouvements) {
            months.add((String) result[0]); // Assuming the month is stored as a string
            Object count = result[1];
            if (count instanceof BigInteger) {
                monthlyCounts.add(((BigInteger) count).longValue()); // Convert BigInteger to Long
            } else if (count instanceof Long) {
                monthlyCounts.add((Long) count); // Directly add Long
            }
        }

        model.addAttribute("totalProduits", produitService.totalProduits());
        model.addAttribute("totalBeneficiaire", beneficiaireService.totalBeneficiaire());
        model.addAttribute("totalMvn", mouvementService.totalMvns());
        model.addAttribute("labels", labels);
        model.addAttribute("counts", counts);
        model.addAttribute("months", months);
        model.addAttribute("monthlyCounts", monthlyCounts);
        model.addAttribute("recentMouvements", recentMouvements);

        return "index";
    }


}
