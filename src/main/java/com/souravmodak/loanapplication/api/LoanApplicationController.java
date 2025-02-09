package com.souravmodak.loanapplication.api;

import com.souravmodak.loanapplication.api.service.LoanApplicationService;
import com.souravmodak.loanapplication.models.LoanAppl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoanApplicationController {

    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @GetMapping("/apply")
    public String showApplicationForm(Model model) {
        model.addAttribute("loanApplication", new LoanAppl());
        return "apply";
    }

    @PostMapping("/submit")
    public String processApplication(@Valid @ModelAttribute LoanAppl loanApplication,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "apply";
        }

        if (loanApplication.getCreditScore() >= 700) {
            loanApplication.setStatus("Approved");
        } else if (loanApplication.getCreditScore() < 500) {
            loanApplication.setStatus("Rejected");
        } else {
            loanApplication.setStatus("Pending");
        }

        service.save(loanApplication);
        model.addAttribute("loanApplication", loanApplication);
        return "status";
    }

    @GetMapping("/status/{id}")
    public String viewApplicationStatus(@PathVariable Long id, Model model) {
        LoanAppl loanApplication = service.findById(id);
        model.addAttribute("loanApplication", loanApplication);
        return "status";
    }
}
