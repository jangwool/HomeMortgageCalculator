package com.example.homemortgage.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Create Mortgage Model class and define methods and return calculations
public class MortgageModel {
    // encapsulate main variables
    private int loanPdYrs;
    private double loanRate;
    private double loanAmount;

    private double principalPmt;
    private double interestPmt;

    // Default Constructor
    public MortgageModel(double loanAmount, double loanRate, int loanPdYrs){
        this.loanAmount = loanAmount;
        this.loanRate = loanRate;
        this.loanPdYrs = loanPdYrs;

        this.principalPmt = 0;
        this.interestPmt = 0;
    }

    // given loan period in years calculate number of monthly payments
    public int calcNumPayments(){
        return loanPdYrs * 12;
    }

    // calculate monthly mortgage payment formula
    public double calcMonthlyPmt(){
        double monthly = (loanRate/12 * loanAmount) /
                ( 1 - ( Math.pow( (1 + loanRate/12), -calcNumPayments() ) ) );
        BigDecimal bd = new BigDecimal(monthly).setScale(2, RoundingMode.HALF_UP);
        monthly = bd.doubleValue();
        return monthly;
    }

    /**
     * calculate monthly interest
     */
    public void calcMonthlyInterest(){
        interestPmt = loanAmount * loanRate / 12;
    }

    /**
     * calculate monthly principal
     */
    public void calcMonthlyPrincipal(){
        calcMonthlyInterest();
        principalPmt = calcMonthlyPmt() - interestPmt;
    }

    /**
     * Getter for interest payment
     * @return double value
     */
    public double getInterestPmt(){
        return interestPmt;
    }

    /**
     * Getter method principal payment
     * @return double value
     */
    public double getPrincipalPmt(){
        return principalPmt;
    }



}
