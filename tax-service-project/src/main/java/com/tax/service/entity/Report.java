package com.tax.service.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String report;
    private String comment;
    private String nameOfReport;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
