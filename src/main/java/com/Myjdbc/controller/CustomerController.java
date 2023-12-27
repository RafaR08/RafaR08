package com.Myjdbc.controller;

import com.Myjdbc.Service.FechasServices;
import com.Myjdbc.entity.Customer;
import com.Myjdbc.entity.Fechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("Customer")
public class CustomerController {


    @Autowired
    FechasServices fechaService;

    @GetMapping("insertar")
    public ResponseEntity<Object> createEmployee(@RequestBody Fechas fecha) {
        return null;
    }
}