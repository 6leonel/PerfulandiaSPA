package controller;

import model.Report;
import service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ReportingController {

    private ReportingService reportingService;

    public Report getSalesSummary() {
        return reportingService.getSalesSummary();
    }

    public Report getLogisticsSummary() {
        return reportingService.getLogisticsSummary();
    }
}
