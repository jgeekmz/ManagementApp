package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.Country;
import com.jgeekmz.ManagementApp.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CountryController {
    @Autowired private CountryService countryService;

    //Get all countries from database
    @GetMapping("/countries")
    public String getCountries(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "country";
    }

    // Find Country by ID
    @RequestMapping("countries/findById")
    @ResponseBody
    public Optional<Country> findById(Integer id) {
        return countryService.findById(id);
    }

    // Add New Country
    @PostMapping(value = "countries/addNew")
    public String addNew(Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }

    //Update Country
    @RequestMapping(value = "countries/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Country country) {
        countryService.save(country);
        return "redirect:/countries";
    }

    // Delete Country
    @RequestMapping(value = "countries/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        countryService.delete(id);
        return "redirect:/countries";
    }
}