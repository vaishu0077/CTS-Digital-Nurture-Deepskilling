package com.cts.countryservice.service;

import com.cts.countryservice.model.Country;
import com.cts.countryservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository repository;

    public Country getCountry(String code){
        return repository.findById(code).orElse(null);
    }

    public Country addCountry(Country country){
        return repository.save(country);
    }

    public Country updateCountry(Country country){
        return repository.save(country);
    }

    public void deleteCountry(String code){
        repository.deleteById(code);
    }

    public List<Country> searchCountry(String name){
        return repository.findByCoNameContainingIgnoreCase(name);
    }
}
