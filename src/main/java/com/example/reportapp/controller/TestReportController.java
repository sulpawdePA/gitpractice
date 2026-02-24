package com.example.reportapp.controller;

import com.example.reportapp.model.TestReport;
import com.example.reportapp.repository.TestReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class TestReportController {
    private final TestReportRepository repo;

    public TestReportController(TestReportRepository repo) { this.repo = repo; }

    @PostMapping
    public ResponseEntity<TestReport> create(@RequestBody TestReport r) {
        if (r.getTimestamp() == null) r.setTimestamp(LocalDateTime.now());
        TestReport saved = repo.save(r);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<TestReport> list() { return repo.findAll(); }
}
