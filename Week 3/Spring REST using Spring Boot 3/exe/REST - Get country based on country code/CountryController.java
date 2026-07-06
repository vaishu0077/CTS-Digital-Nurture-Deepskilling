package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/country")
    public Country getCountryIndia() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        return context.getBean("in", Country.class);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        List<Country> countries = new ArrayList<>();
        countries.add(context.getBean("in", Country.class));
        countries.add(context.getBean("us", Country.class));
        countries.add(context.getBean("jp", Country.class));
        countries.add(context.getBean("de", Country.class));

        return countries;
    }

    @GetMapping("/country/{code}")
    public Country getCountry(@PathVariable String code) {
        return countryService.getCountry(code);
    }
}
