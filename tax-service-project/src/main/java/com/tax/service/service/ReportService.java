package com.tax.service.service;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.entity.Status;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ReportService {

    Report saveReport(ReportDTO reportDTO);

    Page<Report> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String userEmail);

    Page<Report> findPaginatedInspector(int pageNo, int pageSize, String sortField, String sortDirection, String status);

    void updateReport (ReportDTO report);

    void updateStatusAndComment(Long id, String comment, final String status);

    void deleteReport (final Long id);

    Report findById(Long id);
}
