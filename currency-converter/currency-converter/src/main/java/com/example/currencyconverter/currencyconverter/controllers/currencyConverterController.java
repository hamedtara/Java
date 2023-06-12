package com.example.currencyconverter.currencyconverter.controllers;

import com.example.currencyconverter.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//this class should receive your request and should not include any logic ,
// this class usually calls your services class which usually include business logic
@RestController
@RequestMapping("/currencies")
public class currencyConverterController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping()
    public Map<String, Double> getCurrencies(){
        return currencyService.getRates();
    }

}


