package com.example.demo.language;

import com.example.demo.country.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @Column(name = "language_id")
    private Long id;
    private String language;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "country_languages",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    @JsonIgnore
    private Set<Country> countries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
