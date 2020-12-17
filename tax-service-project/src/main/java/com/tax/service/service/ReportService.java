package com.tax.service.service;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import org.springframework.data.domain.Page;


public interface ReportService {

    Report saveReport(ReportDTO reportDTO);

    Page<Report> findPaginated(int currentPage, int pageSize, String sortField, String sortDirection, String userEmail);

    Page<Report> findPaginatedInspector(int currentPage, int pageSize, String sortField, String sortDirection, String status);

    void updateReport (ReportDTO report);

    void updateStatusAndComment(Long id, String comment, final String status);

    void deleteReport (final Long id);

    Report findById(Long id);
}
