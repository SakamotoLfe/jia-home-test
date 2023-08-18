package com.jia.loan.projections.controller;

import com.jia.loan.projections.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class created to be the Controller for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @since 2023-08-18
 * @see LoanService
 */

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
}
