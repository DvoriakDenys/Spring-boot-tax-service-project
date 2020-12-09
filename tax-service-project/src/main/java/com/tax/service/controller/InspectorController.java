package com.tax.service.controller;


import com.tax.service.dto.ReportDTO;
import com.tax.service.entity.Report;
import com.tax.service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/report")
    public String reportPage(Model model){
        Iterable<Report> reports = reportService.findAll();
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
