package com.tax.service.service;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ReportService {

    Report saveReport(ReportDTO reportDTO);

    Page<Report> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String userEmail);

    void updateReport (ReportDTO report);

    void updateComment(Long id, String comment);

    void deleteReport (final Long id);

    Report findById(Long id);

//    Report findReportByUserEmail(String email);
//
//    Report saveReportByUserId();
}
