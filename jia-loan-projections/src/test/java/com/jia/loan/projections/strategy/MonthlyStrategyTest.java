package com.jia.loan.projections.strategy;

import com.jia.loan.projections.exception.LoanDateLimitException;
import com.jia.loan.projections.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.convert.DataSizeUnit;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyStrategyTest {

    @InjectMocks
    private MonthlyStrategy monthlyStrategy;

    @BeforeEach
    void setUp() {
        monthlyStrategy = new MonthlyStrategy();
    }

    @Test
    @DisplayName("whenVerifyDataRangeReturnOK")
    void whenVerifyDataRangeReturnOK() {
        assertDoesNotThrow(() -> monthlyStrategy.verifyDataRange(2));
    }

    @Test
    @DisplayName("whenCalculateFeeReturnOK")
    void whenCalculateFeeReturnOK() {
        assertDoesNotThrow(() -> monthlyStrategy.calculateFee(new BigDecimal(50000), new Date(), 4));
    }

    @Test
    @DisplayName("whenCalculateInstallmentReturnOK")
    void whenCalculateInstallmentReturnOK() {
        assertDoesNotThrow(() -> monthlyStrategy.calculateInstallment(new BigDecimal(50000), new Date(), 4));
    }

    @Test
    @DisplayName("whenVerifyDataRangeReturnError")
    void whenVerifyDataRangeReturnError() {
        assertThrows(LoanDateLimitException.class, () -> monthlyStrategy.verifyDataRange(20));
    }

    @Test
    @DisplayName("whenCalculateFeeReturnError")
    void whenCalculateFeeReturnError() {
        assertThrows(LoanNotFoundException.class, () -> monthlyStrategy.calculateFee(null, new Date(), 4));
    }

    @Test
    @DisplayName("whenCalculateInstallmentReturnError")
    void whenCalculateInstallmentReturnError() {
        assertThrows(LoanNotFoundException.class, () -> monthlyStrategy.calculateInstallment(new BigDecimal(50000), null, 4));
    }
}