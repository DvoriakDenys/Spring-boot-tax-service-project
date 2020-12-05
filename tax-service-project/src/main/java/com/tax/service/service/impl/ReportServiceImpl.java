package com.tax.service.service.impl;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.repository.ReportRepository;
import com.tax.service.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    //TODO Mapstruct

    @Override
    public Report saveReport(ReportDTO reportDTO) {
        final Report report = builderReport(reportDTO);
        return reportRepository.save(report);
    }


    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }


    @Override
    public Report findById(final Long id) {
        return reportRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void updateReport (final ReportDTO report){
        reportRepository.save(builderReport(report));
    }

    public void deleteReport (final Long id){
        reportRepository.deleteById(id);
    }

    public Report builderReport(final ReportDTO reportDTO){
        return Report.builder()
                .id(reportDTO.getId())
                .name(reportDTO.getName())
                .email(reportDTO.getEmail())
                .report(reportDTO.getReport())
                .build();
    }

}



