package com.jia.loan.projections.strategy;

import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.exception.LoanNotFoundException;
import com.jia.loan.projections.indicator.ErrorIndicator;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private static final BigDecimal HALF = new BigDecimal("0.5");

    private static final BigDecimal FIFTHY = new BigDecimal(50);


    /**
     * Method that will verify if the request is in the supported duration range.
     *
     * @param duration that will be tested.
     * @throws LoanDateLimitException will be thrown if the duration is not into a supported range.
     */
    @Override
    public void verifyDataRange(Integer duration) throws LoanDateLimitException {
        if (duration < LoanTypeIndicator.WEEKLY.getMinDuration() ||
                duration > LoanTypeIndicator.WEEKLY.getMaxDuration()) {
            throw new LoanDateLimitException(ErrorIndicator.JLP_DL_01.getMessage());
        }
    }

    /**
     * Method that will calculate the fee.
     *
     * @param amount   to be calculated.
     * @param date     that will start the fee process.
     * @param duration of the loan.
     * @return {@link Set<LoanResponseDTO>} with the calculated fees.
     */
    @Override
    public Set<LoanResponseDTO> calculateFee(BigDecimal amount, Date date, Integer duration) throws LoanNotFoundException {
        checkData(amount, date, duration);
        Set<LoanResponseDTO> response = new HashSet<>();
        BigDecimal onePercentValue = amount.divide(ONE_HUNDRED, RoundingMode.CEILING);
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        duration++;
        for (int i = 1; i < duration; i++) {
            response.add(new LoanResponseDTO(calendar.getTime(), onePercentValue));
            if (i % 2 == 0) {
                BigDecimal serviceFee = onePercentValue.multiply(HALF);
                if (serviceFee.compareTo(FIFTHY) > 0) {
                    serviceFee = FIFTHY;
                }
                response.add(new LoanResponseDTO(calendar.getTime(), serviceFee));
            }
            calendar.add(Calendar.DAY_OF_WEEK, 7);
        }

        return response;
    }

    /**
     * Method that will calculate the installment.
     *
     * @param amount   to be calculated.
     * @param date     that will start the installment process.
     * @param duration of the loan.
     * @return {@link Set<LoanResponseDTO>} with the calculated installments.
     */
    @Override
    public Set<LoanResponseDTO> calculateInstallment(BigDecimal amount, Date date, Integer duration) throws LoanNotFoundException {
        checkData(amount, date, duration);
        Set<LoanResponseDTO> toProcess = calculateFee(amount, date, duration);
        Set<LoanResponseDTO> response = new HashSet<>();
        toProcess.forEach(loanResponseDTO -> {
            var calendar = Calendar.getInstance();
            calendar.setTime(loanResponseDTO.getDate());
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            loanResponseDTO.setDate(calendar.getTime());
            loanResponseDTO.setAmount(loanResponseDTO.getAmount()
                    .add(amount.divide(new BigDecimal(duration), RoundingMode.DOWN)));
            if (response.stream().anyMatch(responseDTO -> responseDTO.getDate().equals(loanResponseDTO.getDate()))) {
                var sameData = response.stream()
                        .filter(responseDTO -> responseDTO.getDate().equals(loanResponseDTO.getDate()))
                        .findFirst().get();
                loanResponseDTO.setAmount(loanResponseDTO.getAmount()
                        .add(sameData.getAmount().subtract(amount.divide(new BigDecimal(duration), RoundingMode.DOWN))));
                response.removeIf(responseDTO -> responseDTO.getDate().equals(loanResponseDTO.getDate()));
            }
            response.add(loanResponseDTO);
        });
        return response;
    }

    /**
     * Method created to check if any field is null.
     *
     * @param amount   to be checked.
     * @param date     that will start the installment process to be checked.
     * @param duration of the loan to be checked.
     * @throws LoanNotFoundException will be thrown if any field is null.
     */
    private void checkData(BigDecimal amount, Date date, Integer duration) throws LoanNotFoundException {
        if (amount == null || date == null || duration == null) {
            throw new LoanNotFoundException(ErrorIndicator.JLP_NF_01.getMessage());
        }
    }
}
