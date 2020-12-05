package com.tax.service.repository;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
