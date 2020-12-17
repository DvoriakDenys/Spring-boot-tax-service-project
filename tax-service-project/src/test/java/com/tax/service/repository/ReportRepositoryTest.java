package com.tax.service.repository;

import com.tax.service.entity.Report;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void findAll_doesNotThrowExceptions() {
        final List<Report> all = reportRepository.findAll();
        assertNotNull(all);
    }
}