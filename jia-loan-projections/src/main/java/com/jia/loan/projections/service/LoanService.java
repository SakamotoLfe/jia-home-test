package com.jia.loan.projections.service;

import com.jia.loan.projections.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class created to be the Service for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @since 2023-08-18
 * @see LoanRepository
 */

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
}
