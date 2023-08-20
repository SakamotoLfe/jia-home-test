package com.jia.loan.projections.controller;

import com.jia.loan.projections.dto.LoanRequestDTO;
import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.exception.LoanBadRequestException;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.exception.LoanNotFoundException;
import com.jia.loan.projections.indicator.ErrorIndicator;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import com.jia.loan.projections.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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

    /**
     * Loan service instance.
     */
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Method created to be the endpoint that will answer to fee projections.
     *
     * @param loanRequestDTO Fee projections' request DTO.
     * @return {@link Set<LoanResponseDTO>} with all the projections.
     * @throws LoanBadRequestException will be thrown if the request is malformed.
     * @throws LoanDateLimitException  will be thrown if the duration is not in the supported range.
     */
    @PostMapping("/fee-projections")
    public Set<LoanResponseDTO> feeProjections(@RequestBody LoanRequestDTO loanRequestDTO) throws LoanBadRequestException, LoanDateLimitException, LoanNotFoundException {
        if (LoanTypeIndicator.WEEKLY.equals(loanRequestDTO.getLoanTypeIndicator())) {
            return loanService.calculateWeeklyFee(loanRequestDTO);
        } else if (LoanTypeIndicator.MONTHLY.equals(loanRequestDTO.getLoanTypeIndicator())) {
            return loanService.calculateMonthlyFee(loanRequestDTO);
        } else {
            throw new LoanBadRequestException(ErrorIndicator.JLP_BR_01.getMessage());
        }
    }

    /**
     * Method created to be the endpoint that will answer to installments' projections.
     *
     * @param loanRequestDTO Installment projections' request DTO.
     * @return {@link Set<LoanResponseDTO>} with all the installments.
     * @throws LoanBadRequestException will be thrown if the request is malformed.
     * @throws LoanDateLimitException  will be thrown if the duration is not in the supported range.
     */
    @PostMapping("/installment-projections")
    public Set<LoanResponseDTO> installmentProjections(@RequestBody LoanRequestDTO loanRequestDTO) throws LoanDateLimitException, LoanBadRequestException, LoanNotFoundException {
        if (LoanTypeIndicator.WEEKLY.equals(loanRequestDTO.getLoanTypeIndicator())) {
            return loanService.calculateWeeklyInstallment(loanRequestDTO);
        } else if (LoanTypeIndicator.MONTHLY.equals(loanRequestDTO.getLoanTypeIndicator())) {
            return loanService.calculateMonthlyInstallment(loanRequestDTO);
        } else {
            throw new LoanBadRequestException(ErrorIndicator.JLP_BR_01.getMessage());
        }
    }
}
