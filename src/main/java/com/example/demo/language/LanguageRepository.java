package com.example.demo.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    public List<Language> findByCountries_Id(Long countryId);

    @Query(value = "SELECT l.language_id, l.language, cl.official FROM languages l JOIN country_languages cl ON l.language_id = cl.language_id WHERE cl.country_id = :countryId",
    nativeQuery = true)
    public List<LanguageDTO> findByCountriesId(@Param("countryId") Long countryId);
}
