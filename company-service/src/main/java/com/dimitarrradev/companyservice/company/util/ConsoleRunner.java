package com.dimitarrradev.companyservice.company.util;

import com.dimitarrradev.companyservice.company.dao.CompanyRepository;
import com.dimitarrradev.companyservice.company.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        companyRepository.saveAndFlush(new Company(null,
                "Company",
                "Company Description",
                new LinkedHashSet<>(Set.of(1L)),
                new LinkedHashSet<>(Set.of(1L)))
        );
    }
}
