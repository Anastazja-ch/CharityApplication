package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listOfInstitutions(Model model) {
        List<Institution> institutionList = institutionRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("institutions", institutionList);
        model.addAttribute("totalBags", donationRepository.sumQuantities());
        model.addAttribute("supportedOrganizations", institutionRepository.count());
        model.addAttribute("categories", categoryList);
        model.addAttribute("donation", new Donation());
        return "index";
    }

}
