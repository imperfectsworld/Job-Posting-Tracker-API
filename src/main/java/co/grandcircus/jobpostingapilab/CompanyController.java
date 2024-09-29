package co.grandcircus.jobpostingapilab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository compRepo;

    @GetMapping("/")
    public String getDefault() {
        return "Default Method";
    }

    @GetMapping
    public List<Company> getAllCompanies(@RequestParam(required = false) String name) {
        if (name != null) {
            return compRepo.findByNameContaining(name);
        }
        return compRepo.findAll();
    }

    @GetMapping("/Company/{id}")
    public Company GetById(@PathVariable("id") Long id) {
        return this.compRepo.findById(id).orElse(null);
    }

    @PostMapping("/Jobs")
    public Company AddPost(@RequestBody Company newcomp) {
        newcomp.setId(null);
        this.compRepo.save(newcomp);
        return newcomp;
    }

}
