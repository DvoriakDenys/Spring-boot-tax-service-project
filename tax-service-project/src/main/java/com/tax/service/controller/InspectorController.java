package com.tax.service.controller;


import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/inspector")
public class InspectorController {

    private final ReportService reportService;

    public InspectorController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public String main (){
        return "inspector/main-inspector";
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
