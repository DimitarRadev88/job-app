package com.dimitarrradev.job_app.web.company.dao;

import com.dimitarrradev.job_app.web.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
