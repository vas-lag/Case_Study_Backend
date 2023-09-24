package com.example.demo.country;

import com.example.demo.countrystats.CountryStats;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.extendedcountrystats.ExtendedCountryStats;
import com.example.demo.extendedcountrystats.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<CountryStats> getCountryStats() {
        return countryRepository.findCountryStats();
    }

    @Override
    public Page<ExtendedCountryStats> getExtendedCountryStats(Optional<String> region, Optional<Integer> yearFrom, Optional<Integer> yearTo, Integer pageNumber, Integer pageSize) {
        Integer yearStart = yearFrom.orElse(1900);
        Integer yearEnd = yearTo.orElse(2050);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (region.isPresent()) {
            return countryRepository.findExtendedCountryStats(region.get(), yearStart, yearEnd, pageable);
        } else {
            return countryRepository.findExtendedCountryStats(yearStart, yearEnd, pageable);
        }
    }

    @Override
    public Years getExtendedCountryStatsYears() {
        return countryRepository.getExtendedCountryStatsYears();
    }
}
