package me.ollari.backendesamejava.AnnualFee;

import me.ollari.backendesamejava.Member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "annual_fee")
public class AnnualFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "price")
    private Double price;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "end_subscription_date")
    private LocalDate endSubscriptionDate;

    public LocalDate getEndSubscriptionDate() {
        return endSubscriptionDate;
    }

    public void setEndSubscriptionDate(LocalDate endSubscriptionDate) {
        this.endSubscriptionDate = endSubscriptionDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}