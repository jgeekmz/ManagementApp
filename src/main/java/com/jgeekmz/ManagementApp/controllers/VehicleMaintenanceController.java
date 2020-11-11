package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.VehicleMaintenance;
import com.jgeekmz.ManagementApp.services.SupplierService;
import com.jgeekmz.ManagementApp.services.VehicleMaintenanceService;
import com.jgeekmz.ManagementApp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleMaintenanceController {
    @Autowired private VehicleMaintenanceService vehicleMaintenanceService;
    @Autowired private VehicleService vehicleService;
    @Autowired private SupplierService supplierService;

    //Get All VehicleMaintenances
    @GetMapping("vehicleMaintenances")
    public String findAll(Model model) {
        model.addAttribute("vehicleMaintenances", vehicleMaintenanceService.findAll());
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "vehicleMaintenance";
    }

    @RequestMapping("vehicleMaintenances/findById")
    @ResponseBody
    public Optional<VehicleMaintenance> findById(Integer id) {
        return vehicleMaintenanceService.findById(id);
    }

    //Add VehicleMaintenance
    @PostMapping(value = "vehicleMaintenances/addNew")
    public String addNew(VehicleMaintenance vehicleMaintenance) {
        vehicleMaintenanceService.save(vehicleMaintenance);
        return "redirect:/vehicleMaintenances";
    }

    @RequestMapping(value = "vehicleMaintenances/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(VehicleMaintenance vehicleMaintenance) {
        vehicleMaintenanceService.save(vehicleMaintenance);
        return "redirect:/vehicleMaintenances";
    }

    @RequestMapping(value = "vehicleMaintenances/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        vehicleMaintenanceService.delete(id);
        return "redirect:/vehicleMaintenances";
    }
}