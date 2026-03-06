package com.dimitarrradev.job_app.company.dao;

import com.dimitarrradev.job_app.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
