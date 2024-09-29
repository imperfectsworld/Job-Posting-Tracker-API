package co.grandcircus.jobpostingapilab;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;


public interface JobRepository extends JpaRepository<JobPosting, Long> 

{
    List<JobPosting> findByTitleContainingIgnoringCase(String title);
    List<JobPosting> findByApplied(Boolean applied);
    List<JobPosting> findByDateAppliedAfter(LocalDate dateApplied);
    List<JobPosting> findByCompanyContainingIgnoringCase(Company company);
}
