package com.example.reportapp.repository;

import com.example.reportapp.model.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestReportRepository extends JpaRepository<TestReport, Long> {

}
