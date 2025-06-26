package com.employee.managment.demo.service;


import com.employee.managment.demo.entity.ManagerEntity;
import com.employee.managment.demo.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    public List<ManagerEntity> getAllManagers(){
        return managerRepository.findAll();
    }

    public ManagerEntity getManager(int id){
        return managerRepository.findById(id).orElseThrow(()  -> new OpenApiResourceNotFoundException("Manager not found with id: " + id));
    }

    @Transactional
    public int updateManagerStatus(boolean status, int managerId){
        return managerRepository.updateManagerStatus(status, managerId);
    }
}
