package com.example.currencyconverter.currencyconverter.repository;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CurrencyRepository {
    public List<String> findAllCurrencies(){
       return Arrays.asList("USD","EUR","GBP");
    }
}
