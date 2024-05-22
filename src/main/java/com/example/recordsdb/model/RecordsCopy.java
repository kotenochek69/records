package com.example.recordsdb.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "records_copy")
public class RecordsCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price_in")
    private Integer price_in;

    @Column(name = "price_out")
    private Integer price_out;

    @Column(name = "year")
    private Integer year;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "rarity")
    private Integer rarity;

    @Column(name = "sold")
    private Boolean sold;
    @ManyToOne
    @JoinColumn(name="producers_id")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name="record_id")
    private RecordEntity record;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

}
