package com.jia.loan.projections.strategy;

import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.exception.LoanDateLimitException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Interface created to be the Strategy for Loans.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @since 2023-08-18
 */

public interface LoanStrategy {

    /**
     * Method that will verify if the request is in the supported duration range.
     *
     * @param duration that will be tested.
     * @throws LoanDateLimitException will be thrown if the duration is not into a supported range.
     */
    void verifyDataRange(Integer duration) throws LoanDateLimitException;

    /**
     * Method that will calculate the fee.
     *
     * @param amount   to be calculated.
     * @param date     that will start the fee process.
     * @param duration of the loan.
     * @return {@link Set<LoanResponseDTO>} with the calculated fees.
     */
    Set<LoanResponseDTO> calculateFee(BigDecimal amount, Date date, Integer duration);

    /**
     * Method that will calculate the installment.
     *
     * @param amount   to be calculated.
     * @param date     that will start the installment process.
     * @param duration of the loan.
     * @return {@link Set<LoanResponseDTO>} with the calculated installments.
     */
    Set<LoanResponseDTO> calculateInstallment(BigDecimal amount, Date date, Integer duration);


}
