package com.tax.service.controller;


import com.tax.service.entity.Report;
import com.tax.service.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/inspector")
public class InspectorController {

    private final ReportService reportService;

    public InspectorController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public String main(Model model) {

        findPaginate(1, "createdDate", "asc", null, model);
        return "inspector/main-inspector";
    }


    @GetMapping("report/page/{currentPage}")
    public String findPaginate(@PathVariable(name = "currentPage") final int currentPage,
                               @RequestParam(name = "sortField", defaultValue = "id") final String sortField,
                               @RequestParam(name = "sortDirection", defaultValue = "asc") final String sortDirection,
                               @RequestParam(name = "status", required = false) String status, Model model) {
        int pageSize = 5;

        Page<Report> page = reportService.findPaginatedInspector(currentPage, pageSize, sortField, sortDirection, status);
        List<Report> reports = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reports", reports);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        return "inspector/report";
    }


    @GetMapping("/report/{id}")
    public String editClientReportPage(@PathVariable(value = "id") Long id, Model model) {
        Report editReport = reportService.findById(id);
        model.addAttribute("editReport", editReport);
        return "inspector/report-edit";
    }

    @PostMapping("/report/edit")
    public String editClientReportAction(final Long id, final String comment, String status) {
        reportService.updateStatusAndComment(id, comment, status);
        return "redirect:/inspector";
    }
}

