package com.project.hant.controller.apis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.hantn.dao.AccountDao;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/user/api")
public class LoginApisMngController {
    @PostMapping("/login")
    public ResponseEntity<?> createProject(@RequestBody AccountDao accountDao) {
        return null;
    }
}
