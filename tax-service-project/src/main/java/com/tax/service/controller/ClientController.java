package com.tax.service.controller;

import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.security.util.SecurityHelper;
import com.tax.service.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/client")
public class ClientController {

    private final ReportService reportService;

    public ClientController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public String getClientPage(Model model) {
        findPaginate(1, "createdDate", "asc", model);
        return "main-client";
    }

    @GetMapping("report/page/{currentPage}")
    public String findPaginate(@PathVariable(name = "currentPage") final int currentPage,
                               @RequestParam(name = "sortField", defaultValue = "id") final String sortField,
                               @RequestParam(name = "sortDirection", defaultValue = "asc") final String sortDirection,
                               Model model) {
        int pageSize = 5;

        Page<Report> page = reportService.findPaginated(currentPage, pageSize, sortField, sortDirection,
                SecurityHelper.extractEmailFromContext());

        List<Report> reports = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reports", reports);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        return "report";
    }

    @GetMapping("/report/add")
    public String addClientReportPage(ReportDTO reportDTO, Model model) {
        model.addAttribute("reportDTO", reportDTO);
        return "report-add";
    }

    @PostMapping("/report/add")
    public String addClientReportAction(@Valid ReportDTO reportDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "report-add";
        }
        else {
            log.info("Report payload:{}", reportDTO);
            reportService.saveReport(reportDTO);
            return "main-client";
        }
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
        return "redirect:/client/report/page/1";
    }

    @PostMapping("/report/remove/{id}")
    public String deleteClientReportAction(@PathVariable Long id) {
        reportService.deleteReport(id);
        return "redirect:/client/report/page/1";
    }


}

