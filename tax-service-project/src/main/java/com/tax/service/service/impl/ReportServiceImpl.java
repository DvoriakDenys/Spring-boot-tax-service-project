package com.tax.service.service.impl;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.repository.ReportRepository;
import com.tax.service.repository.StatusRepository;
import com.tax.service.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final StatusRepository statusRepository;

    //TODO Mapstruct

    @Override
    public Report saveReport(ReportDTO reportDTO) {
        final Report report = builderReport(reportDTO);
        return reportRepository.save(report);
    }

    @Override
    public Page<Report> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String email) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return reportRepository.findByUserEmail(email, pageable);
    }


//    @Override
//    public List<Report> findAll() {
//        return reportRepository.findAll();
//    }

    @Override
    public Report findById(final Long id) {
        log.info("Get report by id:{}", id);
        return reportRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void updateReport(final ReportDTO report) {
        log.info("Save report:{}", report);
        reportRepository.save(builderReport(report));
    }

    @Override
    public void updateComment(final Long id, final String comment) {
        final Report report = findById(id);
        report.setComment(comment);
        reportRepository.save(report);
    }

    public void deleteReport(final Long id) {
        log.info("Delete report by id:{}", id);
        reportRepository.deleteById(id);
    }

    public Report builderReport(final ReportDTO reportDTO) {
        return Report.builder()
                .id(reportDTO.getId())
                .lastName(reportDTO.getLastName())
                .firstName(reportDTO.getFirstName())
                .middleName(reportDTO.getMiddleName())
                .email(reportDTO.getEmail())
                .nameOfReport(reportDTO.getNameOfReport())
                .comment(reportDTO.getComment())
                .createdDate(LocalDateTime.now())
                .report(reportDTO.getReport())
                .build();
    }
}
