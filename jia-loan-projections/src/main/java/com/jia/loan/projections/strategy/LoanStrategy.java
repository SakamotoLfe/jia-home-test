package com.jia.loan.projections.strategy;

import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.model.Loan;

import java.math.BigDecimal;

/**
 * Interface created to be the Strategy for Loans.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see Loan
 * @since 2023-08-18
 */

public interface LoanStrategy {

    void verifyDataRange(Integer duration) throws LoanDateLimitException;

    BigDecimal calculateFee(BigDecimal amount, Integer duration);
}
