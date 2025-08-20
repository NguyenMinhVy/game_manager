package vn.shop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSaleDto implements Serializable {

    private Long accountSaleId;

    private Long accountId;

    private Long costPrice;

    private Long sellPrice;

    private LocalDateTime sellDate;

    private LocalDateTime sellCompleteDate;

    private int sellStatus;

    private Long deposit;

    private Long paidAmount;

    private Long outstandingAmount;

    private int loanTermMonths;

    private int paidMonths;

    private Long monthlyPaymentAmount;

    private Long customerId;

    private String noteForSell;
}
