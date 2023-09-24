package com.example.demo.country;

import com.example.demo.language.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id")
    private Long id;
    private String name;
    private Integer area;
    @Column(name = "national_day")
    private LocalDateTime nationalDay;
    @Column(name = "country_code2")
    private String country_code2;
    @Column(name = "country_code3")
    private String country_code3;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(name = "country_languages",
        joinColumns = @JoinColumn(name = "country_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    @JsonIgnore
    private Set<Language> languages = new HashSet<>();

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public LocalDateTime getNationalDay() {
        return nationalDay;
    }

    public void setNationalDay(LocalDateTime nationalDay) {
        this.nationalDay = nationalDay;
    }

    public String getCountry_code2() {
        return country_code2;
    }

    public void setCountry_code2(String country_code2) {
        this.country_code2 = country_code2;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public void setCountryCode3(String country_code3) {
        this.country_code3 = country_code3;
    }

}
