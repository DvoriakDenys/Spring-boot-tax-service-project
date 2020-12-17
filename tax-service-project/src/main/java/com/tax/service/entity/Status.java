package com.tax.service.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
@Table(name = "status")
public class Status {
    public final static String ACCEPT = "ACCEPT";
    public final static String UNCHECKED = "VERIFY";
    public final static String NOT_ACCEPTED = "REFUSE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
}
