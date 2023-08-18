package com.jia.loan.projections.controller;

import com.jia.loan.projections.dto.LoanDTO;
import com.jia.loan.projections.exception.LoanBadRequestException;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.indicator.ErrorIndicator;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import com.jia.loan.projections.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Class created to be the Controller for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see LoanService
 * @since 2023-08-18
 */

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/fee-projections")
    public BigDecimal feeProjections(@RequestBody LoanDTO loanDTO) throws LoanBadRequestException, LoanDateLimitException {
        if (LoanTypeIndicator.WEEKLY.equals(loanDTO.getLoanTypeIndicator())) {
            return loanService.calculateWeeklyFee(loanDTO);
        } else if (LoanTypeIndicator.MONTHLY.equals(loanDTO.getLoanTypeIndicator())) {
            return loanService.calculateMonthlyFee(loanDTO);
        } else {
            throw new LoanBadRequestException(ErrorIndicator.JLP_BR_01.getMessage());
        }
    }

    @PostMapping("/installment-projections")
    public void installmentProjections(@RequestBody LoanDTO loanDTO) {

    }
}
