package co.grandcircus.jobpostingapilab;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class JobController {

    @Autowired
    private JobRepository jobRepo;

    @GetMapping("/")
    public String getDefault() {
        return "Default Method";
    }

   @GetMapping("/Jobs")
   public List<JobPosting> GetAll(
    @RequestParam(required = false) String title,
    @RequestParam(required = false) Boolean applied,
    @RequestParam(required = false) LocalDate dateApplied,
    @RequestParam(required = false) Company company
    ) 
    {
        if(title != null)
        {
          return this.jobRepo.findByTitleContainingIgnoringCase(title);
        }
        if(applied != null)
        {
          return this.jobRepo.findByApplied(applied);
        }
        if(dateApplied != null)
        {
          return this.jobRepo.findByDateAppliedAfter(dateApplied);
        }
        if(company != null)
        {
          return this.jobRepo.findByCompanyContainingIgnoringCase(company);
        }

       return this.jobRepo.findAll();
    }

    @GetMapping("/Jobs/{id}")
    public JobPosting GetById(@PathVariable("id") Long id) {
        return this.jobRepo.findById(id).orElse(null);
    }
    

  @PostMapping("/Jobs")
    public JobPosting AddPost(@RequestBody JobPosting newJob) {
        newJob.setId(null);
        this.jobRepo.save(newJob);
        return newJob;
    }
    
    @PutMapping("/Posts/{id}")
    public JobPosting Update(@PathVariable("id") Long id, @RequestBody JobPosting updated) {
        jobRepo.save(updated);
        return updated;
    }

    @DeleteMapping("/Posts/{id}")
    public void DeletePost(@PathVariable("id") Long id){
        jobRepo.deleteById(id);
    }
   
   
}
