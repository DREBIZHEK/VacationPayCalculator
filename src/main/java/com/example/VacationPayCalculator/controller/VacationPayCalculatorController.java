package com.example.VacationPayCalculator.controller;

import com.example.VacationPayCalculator.service.VacationPayCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPayCalculatorController {


    @Autowired
    private VacationPayCalculator vacationPayCalculator;
    public VacationPayCalculatorController(VacationPayCalculator vacationPayCalculator) {
        this.vacationPayCalculator = vacationPayCalculator;
    }

    @GetMapping("/calculate")
    public double Calculate(@RequestParam int salary, @RequestParam String vacationStart, @RequestParam String vacationEnd){
        return vacationPayCalculator.Calculate(salary, vacationStart,vacationEnd);
    }
}
