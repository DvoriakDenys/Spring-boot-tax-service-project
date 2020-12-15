package com.tax.service.repository;

import com.tax.service.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//TODO Spring data sort, page

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findByUserEmail(String email, Pageable pageable);
}
