package com.souravmodak.loanapplication.api.service;

import com.souravmodak.loanapplication.models.LoanAppl;
import com.souravmodak.loanapplication.repo.LoanApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanAppl save(LoanAppl loanApplication) {
        return repository.save(loanApplication);
    }

    public LoanAppl findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
