package com.jgeekmz.ManagementApp.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrentWeather  implements Serializable {

    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;

    public CurrentWeather(String description, BigDecimal temperature, BigDecimal feelsLike, BigDecimal windSpeed) {
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
    }

    public CurrentWeather () {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather that = (CurrentWeather) o;
        return description.equals(that.description) && temperature.equals(that.temperature) && feelsLike.equals(that.feelsLike) && windSpeed.equals(that.windSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, temperature, feelsLike, windSpeed);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }
}
