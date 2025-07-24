package com.employee.managment.demo.mongo.Service;

import com.employee.managment.demo.mongo.Entity.MetroEntity;
import com.employee.managment.demo.mongo.Repository.MetroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetroService {

    @Autowired
    MetroRepository metroRepository;

    public List<MetroEntity> getAllMetros(){
       return metroRepository.findAll();
    }
}
