package com.tax.service.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
@ToString
public class Role {
    public final static String CLIENT = "CLIENT";
    public final static String INSPECTOR = "INSPECTOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String role;
}

