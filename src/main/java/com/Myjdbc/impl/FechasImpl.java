package com.Myjdbc.impl;

import com.Myjdbc.Repository.FechasRepository;
import com.Myjdbc.Service.FechasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FechasImpl implements FechasServices {

    @Autowired
    FechasRepository customerRepository;


}