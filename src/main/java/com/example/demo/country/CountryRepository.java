package com.example.demo.country;

import com.example.demo.countrystats.CountryStats;
import com.example.demo.extendedcountrystats.ExtendedCountryStats;
import com.example.demo.extendedcountrystats.Years;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public Optional<Country> findById(Long id);

    @Query(
            value = "WITH BestRatio AS (" +
                "SELECT " +
                    "c.country_id, " +
                    "MAX(cs.gdp / cs.population) AS max_ratio " +
                "FROM " +
                    "countries c " +
                "JOIN " +
                    "country_stats cs " +
                "ON " +
                    "c.country_id = cs.country_id " +
                "GROUP BY " +
                    "c.country_id " +
            ") " +
            "SELECT " +
                "c.name, c.country_code3, cs.year, cs.gdp, cs.population " +
            "FROM " +
                "countries c " +
            "JOIN " +
                "country_stats cs " +
            "ON " +
                "c.country_id = cs.country_id " +
            "JOIN " +
                "BestRatio br " +
            "ON " +
                "c.country_id = br.country_id " +
            "WHERE " +
                "cs.gdp / cs.population = br.max_ratio;",
            nativeQuery = true
    )
    public List<CountryStats> findCountryStats();


    @Query(
            value = "SELECT cont.name as continent_name, r.name as region_name, c.name as country_name, cs.year, cs.population, cs.gdp FROM countries c JOIN country_stats cs ON c.country_id = cs.country_id JOIN regions r ON c.region_id = r.region_id JOIN continents cont ON r.continent_id = cont.continent_id WHERE r.name = :region AND cs.year >= :yearFrom AND cs.year <= :yearTo ORDER BY c.name, cs.year",
            nativeQuery = true
    )
    public Page<ExtendedCountryStats> findExtendedCountryStats(@Param("region") String region, @Param("yearFrom") Integer yearFrom, @Param("yearTo") Integer yearTo, Pageable pageable);

    @Query(
            value = "SELECT cont.name as continent_name, r.name as region_name, c.name as country_name, cs.year, cs.population, cs.gdp FROM countries c JOIN country_stats cs ON c.country_id = cs.country_id JOIN regions r ON c.region_id = r.region_id JOIN continents cont ON r.continent_id = cont.continent_id WHERE cs.year >= :yearFrom AND cs.year <= :yearTo  ORDER BY c.name, cs.year",
            nativeQuery = true
    )
    public Page<ExtendedCountryStats> findExtendedCountryStats(@Param("yearFrom") Integer yearFrom, @Param("yearTo") Integer yearTo, Pageable pageable);

    @Query(value = "SELECT MIN(year) AS minYear, MAX(year) AS maxYear FROM country_stats", nativeQuery = true)
    public Years getExtendedCountryStatsYears();
}
