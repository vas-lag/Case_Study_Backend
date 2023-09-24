package com.example.demo.language;

import com.example.demo.country.CountryRepository;
import com.example.demo.country.CountryService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;
    private CountryRepository countryRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, CountryRepository countryRepository) {
        this.languageRepository = languageRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<LanguageDTO> getAllLanguagesByCountryId(Long countryId) {
        if (!countryRepository.existsById(countryId)) {
            throw new ResourceNotFoundException("Country", "country_id", countryId);
        }
        return languageRepository.findByCountriesId(countryId);
    }
}
