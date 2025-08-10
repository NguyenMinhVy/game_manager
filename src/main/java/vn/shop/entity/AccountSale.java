package vn.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_account_sale")
public class AccountSale extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_sale_id")
    private Long accountSaleId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "cost_price")
    private Long costPrice;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Column(name = "sell_date")
    private LocalDateTime sellDate;

    @Column(name = "sell_complete_date")
    private LocalDateTime sellCompleteDate;

    @Column(name = "sell_status")
    private int sellStatus;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "paid_amount")
    private Long paidAmount;

    @Column(name = "outstanding_amount")
    private Long outstandingAmount;

    @Column(name = "loan_term_months")
    private int loanTermMonths;

    @Column(name = "paid_months")
    private int paidMonths;

    @Column(name = "monthly_payment_amount")
    private Long monthlyPaymentAmount;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "note_for_sell")
    private String noteForSell;
}
