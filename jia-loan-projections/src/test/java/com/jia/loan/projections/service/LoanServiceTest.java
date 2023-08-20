package com.jia.loan.projections.service;

import com.jia.loan.projections.dto.LoanRequestDTO;
import com.jia.loan.projections.dto.LoanResponseDTO;
import com.jia.loan.projections.indicator.LoanTypeIndicator;
import com.jia.loan.projections.strategy.MonthlyStrategy;
import com.jia.loan.projections.strategy.WeeklyStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @MockBean
    WeeklyStrategy weeklyStrategy;

    @MockBean
    MonthlyStrategy monthlyStrategy;

    LoanRequestDTO loanRequestDTO;

    LoanResponseDTO loanResponseDTO;

    @BeforeEach
    void setUp() {
        weeklyStrategy = Mockito.mock(WeeklyStrategy.class);
        monthlyStrategy = Mockito.mock(MonthlyStrategy.class);
        loanService = new LoanService(weeklyStrategy, monthlyStrategy);
        loanRequestDTO = new LoanRequestDTO(2, LoanTypeIndicator.WEEKLY, new Date(), BigDecimal.TEN);
        loanResponseDTO = new LoanResponseDTO(new Date(), BigDecimal.TEN);
    }

    @Test
    @DisplayName("whenCalculateWeeklyFeeReturnOK")
    void whenCalculateWeeklyFeeReturnOK() {
        assertDoesNotThrow(() -> loanService.calculateWeeklyFee(loanRequestDTO));
    }

    @Test
    @DisplayName("whenCalculateMonthlyFeeReturnOK")
    void whenCalculateMonthlyFeeReturnOK() {
        assertDoesNotThrow(()-> loanService.calculateMonthlyFee(loanRequestDTO));
    }

    @Test
    @DisplayName("whenCalculateWeeklyInstallmentReturnOk")
    void whenCalculateWeeklyInstallmentReturnOk() {
        assertDoesNotThrow(() -> loanService.calculateWeeklyInstallment(loanRequestDTO));
    }

    @Test
    @DisplayName("whenCalculateMonthlyInstallmentReturnOK")
    void whenCalculateMonthlyInstallmentReturnOK() {
        assertDoesNotThrow(() -> loanService.calculateMonthlyInstallment(loanRequestDTO));
    }
}