package com.jia.loan.projections.service;

import com.jia.loan.projections.dto.LoanDTO;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.repository.LoanRepository;
import com.jia.loan.projections.strategy.MonthlyStrategy;
import com.jia.loan.projections.strategy.WeeklyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Class created to be the Service for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see LoanRepository
 * @since 2023-08-18
 */

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final WeeklyStrategy weeklyStrategy;

    private final MonthlyStrategy monthlyStrategy;

    @Autowired
    public LoanService(LoanRepository loanRepository, WeeklyStrategy weeklyStrategy, MonthlyStrategy monthlyStrategy) {
        this.loanRepository = loanRepository;
        this.weeklyStrategy = weeklyStrategy;
        this.monthlyStrategy = monthlyStrategy;
    }

    /* Custom methods */

    /**
     * Method that calculates the weekly fee.
     *
     * @param loanDTO Needs the DTO to be processed.
     * @return {@link BigDecimal} with the total amount.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public BigDecimal calculateWeeklyFee(LoanDTO loanDTO) throws LoanDateLimitException {
        verifyWeeklyLimit(loanDTO.getLoanDuration());
        return weeklyStrategy.calculateFee(loanDTO.getAmount(), loanDTO.getLoanDuration());
    }

    /**
     * Method that calculates the monthly fee.
     *
     * @param loanDTO Needs the DTO to be processed.
     * @return {@link BigDecimal} with the total amount.
     * @throws LoanDateLimitException will be thrown if
     *                                the duration is not supported.
     */
    public BigDecimal calculateMonthlyFee(LoanDTO loanDTO) throws LoanDateLimitException {
        verifyMonthlyLimit(loanDTO.getLoanDuration());
        return monthlyStrategy.calculateFee(loanDTO.getAmount(), loanDTO.getLoanDuration());
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
