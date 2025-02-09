package com.souravmodak.loanapplication.repo;

import com.souravmodak.loanapplication.models.LoanAppl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanAppl, Long> {
}
