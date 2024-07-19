package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@SessionAttributes("donation")
@Controller
public class DonationController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstitutionRepository institutionRepository;


    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    @GetMapping("/add")
    public String addDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/add")
    public String saveDonationForm(@ModelAttribute("donation") Donation donation, Model model) {
        donationRepository.save(donation);
        Institution institution = institutionRepository.findById(donation.getInstitution().getId()).orElse(null);
        model.addAttribute("institution", institution);
        model.addAttribute("donation", donation);
        return "redirect:/form-confirmation";
    }
    @GetMapping("/form-confirmation")
    public String showConfirmation(@ModelAttribute("donation") Donation donation, Model model) {
        return "form-confirmation";
    }

}

//    @GetMapping("/add")
//    public String addDonationForm(Model model) {
//        model.addAttribute("donation", new Donation());
//        List<Category> categories = categoryRepository.findAll();
//        model.addAttribute("categories", categories);
//        List<Institution> institutions = institutionRepository.findAll();
//        model.addAttribute("institutions", institutions);
//        return "form";
//
//
//    }
//
//
//    @PostMapping("/add")
//    public String saveDonationForm(@ModelAttribute Donation donation, Model model) {
//        donationRepository.save(donation);
//
////        Institution institution = institutionRepository.findById(donation.getId()).orElse(null);
////        model.addAttribute("institution", institution);
////        model.addAttribute("donation", donation);
//
//
//        return "redirect:/form-confirmation";
//
//    }
//
//
//
//
//
//
//}
