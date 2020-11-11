package com.jgeekmz.ManagementApp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.jgeekmz.ManagementApp.models.VehicleMaintenance;
import com.jgeekmz.ManagementApp.repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMaintenanceService {
    @Autowired private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    //Get All VehicleMaintenances
    public List<VehicleMaintenance> findAll(){
        return vehicleMaintenanceRepository.findAll();
    }

    //Get VehicleMaintenance By Id
    public Optional<VehicleMaintenance> findById(int id) {
        return vehicleMaintenanceRepository.findById(id);
    }

    //Delete VehicleMaintenance
    public void delete(int id) {
        vehicleMaintenanceRepository.deleteById(id);
    }

    //Update VehicleMaintenance
    public void save(VehicleMaintenance vehicleMaintenance){

        vehicleMaintenanceRepository.save(vehicleMaintenance);
    }
}