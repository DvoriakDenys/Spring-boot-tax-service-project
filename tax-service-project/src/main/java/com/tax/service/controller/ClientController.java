package com.tax.service.controller;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.entity.Status;
import com.tax.service.service.ReportService;
import com.tax.service.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/client")
public class ClientController {

    private final ReportService reportService;

    public ClientController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public String getClientPage(Model model) {
        findPaginate(1, model);
        return "main-client";
    }

    @GetMapping("report/page/{pageNo}")
    public String findPaginate(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Report> page = reportService.findPaginated(pageNo, pageSize);
        List<Report> reports = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reports", reports);
        return "report";
    }

    @GetMapping("/report/add")
    public String addClientReportPage() {
        return "report-add";
    }

    @PostMapping("/report/add")
    public String addClientReportAction(ReportDTO reportDTO) {
        log.info("Report payload:{}", reportDTO);
        reportService.saveReport(reportDTO);
        return "redirect:/client";
    }

    @GetMapping("/report/{id}")
    public String reportDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Report report = reportService.findById(id);
        model.addAttribute("report", report);
        return "report-details";
    }

    @GetMapping("/report/edit/{id}")
    public String editClientReportPage(@PathVariable(value = "id") Long id, Model model) {
        Report editReport = reportService.findById(id);
        model.addAttribute("editReport", editReport);
        return "report-edit";
    }

    @PostMapping("/report/edit")
    public String editClientReportAction(ReportDTO report) {
        log.info("Report payload:{}", report);
        reportService.updateReport(report);
        return "redirect:/client/report";
    }

    @PostMapping("/report/remove/{id}")
    public String deleteClientReportAction(@PathVariable Long id) {
        reportService.deleteReport(id);
        return "redirect:/client/report/page/1";
    }
}
