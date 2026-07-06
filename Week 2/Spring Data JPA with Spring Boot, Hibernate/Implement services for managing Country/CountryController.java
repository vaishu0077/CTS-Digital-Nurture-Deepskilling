package com.cts.countryservice.controller;

import com.cts.countryservice.model.Country;
import com.cts.countryservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryService service;

    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code){
        return service.getCountry(code);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country){
        return service.addCountry(country);
    }

    @PutMapping
    public Country updateCountry(@RequestBody Country country){
        return service.updateCountry(country);
    }

    @DeleteMapping("/{code}")
    public String deleteCountry(@PathVariable String code){
        service.deleteCountry(code);
        return "Country Deleted";
    }

    @GetMapping("/search/{name}")
    public List<Country> search(@PathVariable String name){
        return service.searchCountry(name);
    }
}
