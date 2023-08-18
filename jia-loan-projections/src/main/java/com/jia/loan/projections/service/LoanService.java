package com.jia.loan.projections.service;

import com.jia.loan.projections.dto.LoanRequestDTO;
import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.strategy.MonthlyStrategy;
import com.jia.loan.projections.strategy.WeeklyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Class created to be the Service for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see WeeklyStrategy
 * @see MonthlyStrategy
 * @since 2023-08-18
 */

@Service
public class LoanService {

    /**
     * WeeklyStrategy's instance.
     */
    private final WeeklyStrategy weeklyStrategy;

    /**
     * MonthlyStrategy's instance.
     */
    private final MonthlyStrategy monthlyStrategy;

    @Autowired
    public LoanService(WeeklyStrategy weeklyStrategy, MonthlyStrategy monthlyStrategy) {
        this.weeklyStrategy = weeklyStrategy;
        this.monthlyStrategy = monthlyStrategy;
    }

    /* Custom methods */

    /**
     * Method that calculates the weekly fee.
     *
     * @param loanRequestDTO Needs the DTO to be processed.
     * @return {@link Set<LoanResponseDTO>} with the fees calculated.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public Set<LoanResponseDTO> calculateWeeklyFee(LoanRequestDTO loanRequestDTO) throws LoanDateLimitException {
        verifyWeeklyLimit(loanRequestDTO.getLoanDuration());
        return weeklyStrategy.calculateFee(loanRequestDTO.getAmount(), loanRequestDTO.getStartDate(), loanRequestDTO.getLoanDuration());
    }

    /**
     * Method that calculates the monthly fee.
     *
     * @param loanRequestDTO Needs the DTO to be processed.
     * @return {@link Set<LoanResponseDTO>} with the fees calculated.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public Set<LoanResponseDTO> calculateMonthlyFee(LoanRequestDTO loanRequestDTO) throws LoanDateLimitException {
        verifyMonthlyLimit(loanRequestDTO.getLoanDuration());
        return monthlyStrategy.calculateFee(loanRequestDTO.getAmount(), loanRequestDTO.getStartDate(), loanRequestDTO.getLoanDuration());
    }

    /**
     * Method that calculates the weekly installment.
     *
     * @param loanRequestDTO Needs the DTO to be processed.
     * @return {@link Set<LoanResponseDTO>} with the installments calculated.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public Set<LoanResponseDTO> calculateWeeklyInstallment(LoanRequestDTO loanRequestDTO) throws LoanDateLimitException {
        verifyWeeklyLimit(loanRequestDTO.getLoanDuration());
        return weeklyStrategy.calculateInstallment(loanRequestDTO.getAmount(), loanRequestDTO.getStartDate(), loanRequestDTO.getLoanDuration());
    }

    /**
     * Method that calculates the monthly installment.
     *
     * @param loanRequestDTO Needs the DTO to be processed.
     * @return {@link Set<LoanResponseDTO>} with the installments calculated.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public Set<LoanResponseDTO> calculateMonthlyInstallment(LoanRequestDTO loanRequestDTO) throws LoanDateLimitException {
        verifyMonthlyLimit(loanRequestDTO.getLoanDuration());
        return monthlyStrategy.calculateInstallment(loanRequestDTO.getAmount(), loanRequestDTO.getStartDate(), loanRequestDTO.getLoanDuration());
    }

    /**
     * Method that verify if the duration is within supported limits.
     *
     * @param duration loan's duration
     * @throws LoanDateLimitException will be thrown
     *                                if the duration is not within the limits.
     */
    private void verifyWeeklyLimit(Integer duration) throws LoanDateLimitException {
        weeklyStrategy.verifyDataRange(duration);
    }

    /**
     * Method that verify if the duration is within supported limits.
     *
     * @param duration loan's duration
     * @throws LoanDateLimitException will be thrown
     *                                if the duration is not within the limits.
     */
    private void verifyMonthlyLimit(Integer duration) throws LoanDateLimitException {
        monthlyStrategy.verifyDataRange(duration);
    }
}
