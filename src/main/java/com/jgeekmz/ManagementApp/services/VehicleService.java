package com.jgeekmz.ManagementApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jgeekmz.ManagementApp.models.Vehicle;
import com.jgeekmz.ManagementApp.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired private VehicleRepository vehicleRepository;

    //Get All Vehicles
    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    // list all vehicles on index page
    public List<Vehicle> listAll() {
        List<Vehicle> cars = new ArrayList<>();
        vehicleRepository.findAll().forEach(cars::add);
        return cars;
    }

        //Get Vehicle By Id
    public Optional<Vehicle> findById(int id) {
        return vehicleRepository.findById(id);
    }

    //Delete Vehicle
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }

    //Update Vehicle
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}