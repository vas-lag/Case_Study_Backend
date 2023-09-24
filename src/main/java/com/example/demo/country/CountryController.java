package com.example.demo.country;

import com.example.demo.countrystats.CountryStats;
import com.example.demo.extendedcountrystats.ExtendedCountryStats;
import com.example.demo.extendedcountrystats.Years;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(countryService.getCountryById(id), HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/countries/stats")
    public ResponseEntity<List<CountryStats>> getCountryStats() {
        return new ResponseEntity<>(countryService.getCountryStats(), HttpStatus.OK);
    }

    @GetMapping("/countries/extendedstats")
    public ResponseEntity<Page<ExtendedCountryStats>> getExtendedCountryStats(@RequestParam Optional<String> region, @RequestParam Optional<Integer> yearFrom, @RequestParam Optional<Integer> yearTo, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        Page<ExtendedCountryStats> extendedCountryStats = countryService.getExtendedCountryStats(region, yearFrom, yearTo, page, size);
        return new ResponseEntity<>(extendedCountryStats, HttpStatus.OK);
    }

    @GetMapping("countries/extendedstats/years")
    public ResponseEntity<Years> getExtendedCountryStatsYears() {
        return new ResponseEntity<>(countryService.getExtendedCountryStatsYears(), HttpStatus.OK);
    }
}
