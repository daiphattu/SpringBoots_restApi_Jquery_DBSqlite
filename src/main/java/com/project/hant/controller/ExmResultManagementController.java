package com.project.hant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin")
public class ExmResultManagementController {
    @GetMapping("/exmResult")
    public ModelAndView exmResult() {
        ModelAndView model = new ModelAndView();

        model.setViewName("admin/exmResult/exm_result_management");

        return model;
    }
}
