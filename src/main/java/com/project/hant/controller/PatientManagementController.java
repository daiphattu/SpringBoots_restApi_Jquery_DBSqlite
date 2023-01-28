package com.project.hant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin")
public class PatientManagementController {
    @GetMapping("/patient")
    public ModelAndView userManager() {
        ModelAndView model = new ModelAndView();

        model.setViewName("admin/patient/patient_management");

        return model;
    }
}
