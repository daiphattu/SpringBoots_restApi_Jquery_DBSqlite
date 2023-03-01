package com.project.hant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin")
public class MedicineManagementController {
    @GetMapping("/medicine")
    public ModelAndView medicineManager() {
        ModelAndView model = new ModelAndView();

        model.setViewName("admin/medicine/medicine_management");

        return model;
    }
}
