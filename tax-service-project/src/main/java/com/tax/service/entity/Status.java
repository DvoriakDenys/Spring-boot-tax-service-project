package com.tax.service.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "status")
@ToString
public class Status {
    public final static String ACCEPT = "ACCEPT";
    public final static String UNCHECKED = "FILED";
    public final static String NOT_ACCEPTED = "NOT_ACCEPTED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
}
