package com.example.reportapp;

import com.example.reportapp.model.TestReport;
import com.example.reportapp.repository.TestReportRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate rest;

    @Autowired
    TestReportRepository repo;

    @BeforeAll
    static void beforeAll(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    void createAndListReport() {
        TestReport r = new TestReport();
        r.setName("it-suite");
        r.setStatus("PASS");
        r.setDetails("ok");

        ResponseEntity<TestReport> resp = rest.postForEntity("/api/reports", r, TestReport.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        TestReport created = resp.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        TestReport[] list = rest.getForObject("/api/reports", TestReport[].class);
        assertThat(list).extracting("id").contains(created.getId());
    }
}
