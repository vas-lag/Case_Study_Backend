package com.example.demo.language;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/countries/{countryId}/languages")
    public ResponseEntity<List<LanguageDTO>> getAllLanguagesByCountryId(@PathVariable("countryId") Long countryId) {
        return new ResponseEntity<>(languageService.getAllLanguagesByCountryId(countryId), HttpStatus.OK);
    }
}
