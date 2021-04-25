package com.jgeekmz.ManagementApp.services;

import com.jgeekmz.ManagementApp.models.CurrentWeather;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StubWeatherService {
    public CurrentWeather getCurrentWeather(String city, String country) {
        return new CurrentWeather("Clear", BigDecimal.ONE,BigDecimal.TEN,BigDecimal.ZERO);
    }
}
