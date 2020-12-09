package com.tax.service.service;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;

import java.util.List;


public interface ReportService {

    Report saveReport(ReportDTO reportDTO);

    List<Report> findAll();

    void updateReport (ReportDTO report);

    void updateComment(Long id, String comment);

    void deleteReport (final Long id);

    Report findById(Long id);

}
