package com.example.demo.language;

import java.util.List;

public interface LanguageService {
    public List<LanguageDTO> getAllLanguagesByCountryId(Long countryId);
}
