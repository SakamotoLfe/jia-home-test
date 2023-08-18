package com.jia.loan.projections.strategy;

import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.indicator.ErrorIndicator;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class created to be the Strategy for Weekly Loans.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see LoanStrategy
 * @since 2023-08-18
 */

@Component
public class WeeklyStrategy implements LoanStrategy {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private static final BigDecimal TWO = new BigDecimal(2);

    private static final BigDecimal FIFTHY = new BigDecimal(50);


    @Override
    public void verifyDataRange(Integer duration) throws LoanDateLimitException {
        if (duration < LoanTypeIndicator.WEEKLY.getMinDuration() ||
                duration > LoanTypeIndicator.WEEKLY.getMaxDuration()) {
            throw new LoanDateLimitException(ErrorIndicator.JLP_DL_01.getMessage());
        }
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount, Integer duration) {
        BigDecimal bigDuration = new BigDecimal(duration);
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal percentage = totalAmount.divide(ONE_HUNDRED, RoundingMode.HALF_UP);
        totalAmount = totalAmount.add(percentage).multiply(bigDuration);

        BigDecimal serviceFeeAmount = bigDuration.divide(TWO, RoundingMode.DOWN);
        serviceFeeAmount = percentage.multiply(serviceFeeAmount);
        if (serviceFeeAmount.compareTo(FIFTHY) > 0) {
            serviceFeeAmount = new BigDecimal(50);
        }
        totalAmount = totalAmount.add(serviceFeeAmount);
        return totalAmount;
    }
}
