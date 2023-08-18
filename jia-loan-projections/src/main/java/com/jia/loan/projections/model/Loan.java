package com.jia.loan.projections.model;

import com.jia.loan.projections.indicator.LoanTypeIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Class created to be the Model for Loans.
 *
 * @author Alfredo marin
 * @version BETA-1.0
 * @since 2023-08-18
 */

@Entity
public class Loan {

    /**
     * Loan's ID.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", type = UuidGenerator.class)
    private UUID id;

    /**
     * Loan's duration.
     */
    private Integer loanDuration;

    /**
     * Loan's duration type.
     */
    private LoanTypeIndicator loanTypeIndicator;

    /**
     * Loan's start date.
     */
    private Date loanStartDate;

    /**
     * Loan amount
     */
    private BigDecimal loanAmount;

    /**
     * Last time the loan entity was updated.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdated;

    /**
     * Date when the loan entry was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createDate;

    /**
     * If the loan entry is enabled or not.
     */
    @Column(nullable = false)
    private boolean enabled;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id                Loan's ID.
     * @param loanDuration      Loan's duration in weeks.
     * @param loanTypeIndicator Loan's duration type.
     * @param loanStartDate     Loan's start date.
     * @param loanAmount        Loan amount
     * @param lastUpdated       Last time the loan entity was updated.
     * @param createDate        Date when the loan entry was created.
     * @param enabled           If the loan entry is enabled or not.
     */
    public Loan(UUID id, Integer loanDuration, LoanTypeIndicator loanTypeIndicator, Date loanStartDate,
                BigDecimal loanAmount, Date lastUpdated, Date createDate, boolean enabled) {
        this.id = id;
        this.loanDuration = loanDuration;
        this.loanTypeIndicator = loanTypeIndicator;
        this.loanStartDate = loanStartDate;
        this.loanAmount = loanAmount;
        this.lastUpdated = lastUpdated;
        this.createDate = createDate;
        this.enabled = enabled;
    }

    /**
     * Empty constructor.
     */
    public Loan() {
        this.enabled = true;
    }

    /* Equals and HashCode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return enabled == loan.enabled && Objects.equals(id, loan.id) && Objects.equals(loanDuration, loan.loanDuration)
                && loanTypeIndicator == loan.loanTypeIndicator && Objects.equals(loanStartDate, loan.loanStartDate)
                && Objects.equals(loanAmount, loan.loanAmount) && Objects.equals(lastUpdated, loan.lastUpdated)
                && Objects.equals(createDate, loan.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanDuration, loanTypeIndicator, loanStartDate, loanAmount, lastUpdated, createDate,
                enabled);
    }

    /* Getters and Setters */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(Integer loanDuration) {
        this.loanDuration = loanDuration;
    }

    public LoanTypeIndicator getLoanTypeIndicator() {
        return loanTypeIndicator;
    }

    public void setLoanTypeIndicator(LoanTypeIndicator loanTypeIndicator) {
        this.loanTypeIndicator = loanTypeIndicator;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Pre persist

    /**
     * Method to automatically put a register date on each register saved on database.
     */
    @PrePersist
    private void putRegDate() {
        this.setCreateDate(new Date());
    }

    // Pre update

    /**
     * Method to automatically put the last date that the register was updated.
     */
    @PreUpdate
    private void putLastUpdate() {
        this.setLastUpdated(new Date());
    }

    /* To String */

    /**
     * To String Method.
     *
     * @return {@link String}. String with all objects' values.
     */
    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanDuration=" + loanDuration +
                ", loanTypeIndicator=" + loanTypeIndicator +
                ", loanStartDate=" + loanStartDate +
                ", loanAmount=" + loanAmount +
                ", lastUpdated=" + lastUpdated +
                ", createDate=" + createDate +
                ", enabled=" + enabled +
                '}';
    }
}
