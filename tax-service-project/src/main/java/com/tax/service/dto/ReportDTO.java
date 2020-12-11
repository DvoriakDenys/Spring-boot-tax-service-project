package com.tax.service.dto;

import com.tax.service.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {

    private Long id;
    private String firstname;
    private String name;
    private String lastname;
    private String email;
    private String report;
    private String createdDate;
    private String comment;
    private Status status;
    private String nameOfReport;

}
