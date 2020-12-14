package com.tax.service.controller;


import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String main (Model model){
        findPaginate(1, "createdDate", "asc",  model);
        return "inspector/main-inspector";
    }


    @GetMapping("report/page/{pageNo}")
    public String findPaginate(@PathVariable(name = "pageNo") final int pageNo,
                               @RequestParam(name = "sortField", defaultValue = "id") final String sortField,
                               @RequestParam( name = "sortDirection", defaultValue = "asc") final String sortDirection,
                               Model model) {
        int pageSize = 5;

        Page<Report> page = reportService.findPaginated(pageNo, pageSize, sortField, sortDirection, null);
        List<Report> reports = page.getContent();

        model.addAttribute("currentPage", pageNo);
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
    public String editClientReportAction(final Long id, final String comment) {
        reportService.updateComment(id, comment);
        return "redirect:/inspector";
    }
}
