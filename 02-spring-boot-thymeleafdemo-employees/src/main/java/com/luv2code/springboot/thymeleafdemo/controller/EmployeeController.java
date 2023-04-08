package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    List<Employee> theEmployeeList;

    // before getting the data adding the employees to list

//    @PostConstruct
//    public void addEmployee(){
//        theEmployeeList = new ArrayList<>();
//
//        Employee employee = new Employee("Rajan","Sharma","rajan@gmail.com");
//        Employee employee1 = new Employee("Abhishek","Kumar","abhishek@gmail.com");
//        Employee employee2 = new Employee("Atul","Ratnam","atul@gmail.com");
//
//        theEmployeeList.add(employee);
//        theEmployeeList.add(employee1);
//        theEmployeeList.add(employee2);
//    }

//    @GetMapping("/signup")
//    public String showSignUpForm(Employee theEmployee) {
//        return "add-employee";
//    }

//    @PostMapping("/addEmployee")
//    public String addUser(@Valid Employee theEmployee, BindingResult result, Model model) {
//        model.addAttribute("theEmployee",theEmployee);
//        if (result.hasErrors()) {
//            return "add-employee";
//        }
//
//        employeeRepository.save(theEmployee);
//        return "redirect:/index";
//    }

    // getting list of employees
    @GetMapping("/index")
    public String getEmployeeList(Model theModel){
        theEmployeeList = iEmployeeService.findAll();
        theModel.addAttribute("theEmployee",theEmployeeList);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String saveEmployee(@PathVariable("id")int id, Model theModel){
        Employee theEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        theModel.addAttribute("theEmployee",theEmployee);
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Employee theEmployee,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            theEmployee.setId(id);
            return "update-employee";
        }

        iEmployeeService.save(theEmployee);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Employee theEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        iEmployeeService.deleteById(theEmployee.getId());
        return "redirect:/index";
    }


    @PostMapping("/addEmployee")
    public String saveEmployees(@ModelAttribute Employee theEmployee, Model theModel){
        theModel.addAttribute("theEmployee",theEmployee);
//        theEmployeeList = new ArrayList<>();
//        theEmployeeList.add(theEmployee);
//        iEmployeeService.save(theEmployee);
        iEmployeeService.save(theEmployee);
        return "index";
    }



}
