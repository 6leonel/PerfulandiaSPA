package controller;

import model.Report;
import service.ReportingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @Operation(summary = "Resumen de ventas", description = "Devuelve un reporte con el resumen total de ventas.")
    @GetMapping("/sales-summary")
    public Report getSalesSummary() {
        return reportingService.getSalesSummary();
    }

    @Operation(summary = "Resumen logístico", description = "Devuelve un reporte con el estado de las operaciones logísticas.")
    @GetMapping("/logistics-summary")
    public Report getLogisticsSummary() {
        return reportingService.getLogisticsSummary();
    }
}
