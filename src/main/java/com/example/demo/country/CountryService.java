package com.example.demo.country;

import com.example.demo.countrystats.CountryStats;
import com.example.demo.extendedcountrystats.ExtendedCountryStats;
import com.example.demo.extendedcountrystats.Years;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Country getCountryById(Long id);
    public List<Country> getAllCountries();
    public List<CountryStats> getCountryStats();
    public Page<ExtendedCountryStats> getExtendedCountryStats(Optional<String> region, Optional<Integer> yearFrom, Optional<Integer> yearTo, Integer pageNumber, Integer pageSize);
    public Years getExtendedCountryStatsYears();
}
