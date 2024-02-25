package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/countries/{id}")
    public Country updateCountry(@PathVariable UUID id, @RequestBody Country country) {
        return countryService.updateCountry(id, country);
    }

}
