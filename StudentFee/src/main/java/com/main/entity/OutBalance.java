package com.main.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
@Table
@Entity
public class OutBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private Double currentAmount;
    private Date transactionDate;
    private Double pastDueAmount;





}
