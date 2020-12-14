package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.exceptions.UserNotFoundException;
import com.jgeekmz.ManagementApp.models.Employee;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.EmployeeRepository;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import com.jgeekmz.ManagementApp.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

   @Autowired private EmployeeService employeeService;
   @Autowired private EmployeeRepository employeeRepository;
   @Autowired private UserRepository rep;

   private StateService stateService;
    private JobTitleService jobTitleService;
    private EmployeeTypeService employeeTypeService;
    private CountryService countryService;
    private UserService userService;

    public EmployeeController(EmployeeService employeeService, StateService stateService, JobTitleService jobTitleService, EmployeeTypeService employeeTypeService, CountryService countryService, UserService userService) {
        this.employeeService = employeeService;
        this.countryService = countryService;
        this.employeeTypeService = employeeTypeService;
        this.stateService = stateService;
        this.jobTitleService = jobTitleService;
        this.userService = userService;
    }

    // Get All Employees
    @GetMapping("employees")
    public String findAll(Model model, String keywordSearch) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("jobTitles", jobTitleService.findAll());
        model.addAttribute("employeeTypes", employeeTypeService.findAll());

        if (keywordSearch != null) {
            model.addAttribute("employees", employeeService.findEmployeeByKeaword(keywordSearch));
        } else {
            model.addAttribute("employees", employeeService.findAll());
        }

        return "employee";
    }

    @RequestMapping("employees/findById")
    @ResponseBody
    public Optional<Employee> findById(Integer id) {
        return employeeService.findById(id);
    }

    // Add Employee
    @PostMapping(value = "employees/addNew")
    public String addNew(Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "employees/update", method = { RequestMethod.PUT, RequestMethod.GET })
    public String update(Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "employees/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String delete(Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/uploadPhoto", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File newFile = new File("D:\\SOLUTIONS\\fleetms\\uploads" + file.getOriginalFilename());
        newFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(newFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }


    @PostMapping("/employees/uploadPhoto2")
    public String uploadFile2(@RequestParam("file") MultipartFile file, Principal principal) throws IllegalStateException, IOException {
        //System.out.println("Principal: " + principal.getName());

        String baseDirectory = "D:\\Downloads\\JGeekMZBootStrap\\src\\main\\resources\\static\\img\\photos\\";
        file.transferTo(new File(baseDirectory + principal.getName() + ".jpg"));
        return "redirect:/profile";
    }

    @RequestMapping(value = "/employees/profile")
    public String profile(Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("employee", employeeService.findByUsername(un));
        return "redirect:/profile";
    }

    // Assign Username to Employee
    @RequestMapping(value = "/employees/assignUsername")
    public String assignUsername(int id, RedirectAttributes redirectAttributes) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        String firstname = emp.getFirstname();
        String lastname = emp.getLastname();
        String userName = rep.findUsernameByFirstnameAndLastname(firstname,lastname);
        List<User> userFound =  employeeService.assignUsername(id);

        if (userFound.isEmpty()) {
            emp.setUsername("No user found!");
            employeeRepository.save(emp);
        } else if (userFound != null && userFound.size() == 1) {
            emp.setUsername(String.valueOf(userName));
            employeeRepository.save(emp);
        } else if (userFound != null && userFound.size() > 1){
            redirectAttributes.addFlashAttribute("msg", "More then one user!");
            return "redirect:/employees";
           //throw new UserNotFoundException("We have more than one user with those names!");
        }

        //model.addAttribute("msg", "More then one user!");
        return "redirect:/employees";
        //return "employee";
    }
}
