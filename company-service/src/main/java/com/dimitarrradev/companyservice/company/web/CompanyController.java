package com.dimitarrradev.companyservice.company.web;

import com.dimitarrradev.companyservice.company.model.Company;
import com.dimitarrradev.companyservice.company.service.CompanyService;
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
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity
                .ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable Long id) {
        Company company = companyService.get(id);
        if (company != null) {
            return ResponseEntity
                    .ok(company);
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PatchMapping("{id}")
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

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody Company company) {
        companyService.create(company);

        return ResponseEntity
                .ok("Created company");
    }

    @DeleteMapping("/{id}")
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

}
