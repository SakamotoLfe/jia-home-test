package com.jia.loan.projections.strategy;

import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WeeklyStrategyTest {

    @InjectMocks
    private WeeklyStrategy weeklyStrategy;

    @BeforeEach
    void setUp() {
        weeklyStrategy = new WeeklyStrategy();
    }

    @Test
    @DisplayName("whenVerifyDataRangeReturnOK")
    void whenVerifyDataRangeReturnOK() {
        assertDoesNotThrow(() -> weeklyStrategy.verifyDataRange(2));
    }

    @Test
    @DisplayName("whenCalculateFeeReturnOK")
    void whenCalculateFeeReturnOK() {
        assertDoesNotThrow(() -> weeklyStrategy.calculateFee(new BigDecimal(50000), new Date(), 4));
    }

    @Test
    @DisplayName("whenCalculateInstallmentReturnOK")
    void whenCalculateInstallmentReturnOK() {
        assertDoesNotThrow(() -> weeklyStrategy.calculateInstallment(new BigDecimal(50000), new Date(), 4));
    }

    @Test
    @DisplayName("whenVerifyDataRangeReturnError")
    void whenVerifyDataRangeReturnError() {
        assertThrows(LoanDateLimitException.class, () -> weeklyStrategy.verifyDataRange(20));
    }

    @Test
    @DisplayName("whenCalculateFeeReturnError")
    void whenCalculateFeeReturnError() {
        assertThrows(LoanNotFoundException.class, () -> weeklyStrategy.calculateFee(null, new Date(), 4));
    }

    @Test
    @DisplayName("whenCalculateInstallmentReturnError")
    void whenCalculateInstallmentReturnError() {
        assertThrows(LoanNotFoundException.class, () -> weeklyStrategy.calculateInstallment(new BigDecimal(50000), null, 4));
    }
}