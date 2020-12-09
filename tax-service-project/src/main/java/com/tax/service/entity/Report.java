package com.tax.service.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstname;
    private String name;
    private String lastname;
    private String email;
    private String report;
    private String comment;
    private String nameOfReport;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;
}
