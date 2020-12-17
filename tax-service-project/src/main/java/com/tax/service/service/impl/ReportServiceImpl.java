package com.tax.service.service.impl;
import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.entity.Status;
import com.tax.service.entity.User;
import com.tax.service.repository.ReportRepository;
import com.tax.service.repository.StatusRepository;
import com.tax.service.repository.UserRepository;
import com.tax.service.security.util.SecurityHelper;
import com.tax.service.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    //TODO Mapstruct

    @Override
    public Report saveReport(ReportDTO reportDTO) {
        final Report report = builderReport(reportDTO);
        return reportRepository.save(report);
    }

    @Override
    public Page<Report> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String email) {
        return reportRepository.findByUserEmail(email, getPageable(pageNo, pageSize, sortField, sortDirection));
    }

    @Override
    public Page<Report> findPaginatedInspector(int pageNo, int pageSize, String sortField, String sortDirection,
                                               String status) {
        if (status == null) {
            return reportRepository.findAll(getPageable(pageNo, pageSize, sortField, sortDirection));
        } else {
            return reportRepository.findAllByStatusName(getPageable(pageNo, pageSize, sortField, sortDirection), status);
        }
    }

    public Pageable getPageable (int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

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
    public void updateStatusAndComment(final Long id, final String comment, final String status) {
        final Report report = findById(id);
        Status findStatus = statusRepository.findStatusByName(status);
        report.setComment(comment);
        report.setStatus(findStatus);
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
                .user(findByEmail(SecurityHelper.extractEmailFromContext()))
                .status(findByName(Status.UNCHECKED))
                .build();
    }

    public User findByEmail(final String email) {
        log.info("Get user by email:{}", email);
        return userRepository.findUserByEmail(email);
    }

    public Status findByName(String name) {
        log.info("Get status by name:{}", name);
        return statusRepository.findStatusByName(name);
    }
}
