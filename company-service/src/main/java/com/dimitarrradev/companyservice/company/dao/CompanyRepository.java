package com.dimitarrradev.companyservice.company.dao;

import com.dimitarrradev.companyservice.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
