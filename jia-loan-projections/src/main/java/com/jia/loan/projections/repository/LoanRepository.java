package com.jia.loan.projections.repository;

import com.jia.loan.projections.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Interface created to be the Repository for Loan.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @see Loan
 * @since 2023-08-18
 */

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
