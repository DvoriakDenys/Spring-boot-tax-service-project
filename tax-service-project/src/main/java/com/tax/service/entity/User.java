package com.tax.service.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = true)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
