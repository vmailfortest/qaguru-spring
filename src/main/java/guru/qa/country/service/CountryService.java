package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll()
                .stream().map(Country::fromEntity).toList();
    }

    public Country addCountry(Country country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName(country.countryName());
        countryEntity.setCountryCode(country.countryCode());

        return Country.fromEntity(countryRepository.save(countryEntity));
    }

    public Country updateCountry(UUID id, Country updatedCountry) {
        Optional<CountryEntity> existingCountry = countryRepository.findById(id);

        if (existingCountry.isEmpty()) {
            throw new EntityNotFoundException("Country ID not found: " + id);
        }

        existingCountry.get().setCountryName(updatedCountry.countryName());

        return Country.fromEntity(countryRepository.save(existingCountry.get()));
    }

}
