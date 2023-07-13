package com.transaction.devsu.controller;

import com.transaction.devsu.dto.TransactionReportDTO;
import com.transaction.devsu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("")
    public List<TransactionReportDTO> getReportByAccountNumberAndDateRange(@RequestParam String identification,
                                                                           @RequestParam String initDate,
                                                                           @RequestParam String endDate){
        return reportService.geTransactionReportByIdentificationAndDateRange(identification, initDate, endDate);

    }

}
