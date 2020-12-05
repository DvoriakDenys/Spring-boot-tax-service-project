package com.tax.service.service;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;

import java.util.List;


public interface ReportService {

//    Report saveReport(ReportDTO reportDTO);
    Report saveReport(ReportDTO reportDTO);

    List<Report> findAll();
//    Report editReport(ReportDTO reportDTO);
    void updateReport (ReportDTO report);
//    Optional<Report> findById(Long id);
    void deleteReport (final Long id);

    Report findById(Long id);

//    Report saveAndAddReport(ReportDTO reportDTO);
}
