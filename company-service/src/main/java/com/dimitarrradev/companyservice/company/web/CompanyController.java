package com.dimitarrradev.companyservice.company.web;

import com.dimitarrradev.companyservice.company.dto.CompanyServiceModel;
import com.dimitarrradev.companyservice.company.model.Company;
import com.dimitarrradev.companyservice.company.service.CompanyService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<CompanyServiceModel>> getAll(){
        return ResponseEntity
                .ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyServiceModel> get(@PathVariable Long id) {
        CompanyServiceModel company = companyService.get(id);
        if (company != null) {
            return ResponseEntity
                    .ok(company);
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("")
    @RateLimiter(name = "createLimiter", fallbackMethod = "createFallback")
    public ResponseEntity<String> create(@RequestBody Company company) {
        companyService.create(company);

        return ResponseEntity
                .ok("Created company");
    }

    @PatchMapping("{id}")
    @RateLimiter(name = "updateLimiter", fallbackMethod = "updateFallback")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.update(id, company);

        if (updated) {
            return ResponseEntity
                    .ok("Company updated successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    @RateLimiter(name = "deleteLimiter", fallbackMethod = "deleteFallback")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isDeleted = companyService.delete(id);

        if (isDeleted) {
            return ResponseEntity
                    .ok("Company deleted successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    private ResponseEntity<String> createFallback(Company company, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> deleteFallback(Long id, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> updateFallback(Long id, Company company, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> getTooManyRequests() {
        return ResponseEntity.status(429).body("Too many requests!");
    }

}
