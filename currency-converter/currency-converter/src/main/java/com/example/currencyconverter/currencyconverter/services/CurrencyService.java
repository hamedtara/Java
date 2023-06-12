package com.example.currencyconverter.currencyconverter.services;

import com.example.currencyconverter.currencyconverter.repository.CurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

// if this class need any data from anywhere including database -api .etc

@Service
public class CurrencyService {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=***";

    public Map<String, Double> getRates() {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeResponse response = restTemplate.getForObject(API_URL, ExchangeResponse.class);
        return response.getRates();
    }

    // Define a class that matches the response from the API
    public static class ExchangeResponse {
        private String base;
        private String date;
        private Map<String, Double> rates;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Map<String, Double> getRates() {
            return rates;
        }

        public void setRates(Map<String, Double> rates) {
            this.rates = rates;
        }
    }
}
