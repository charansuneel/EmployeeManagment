package com.employee.managment.demo.repository;

import com.employee.managment.demo.EmployeeDto;
import com.employee.managment.demo.EmployeeSummary;
import com.employee.managment.demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity,Long>{

    Optional<EmployeeEntity> findByEmployeeId(Long id);

//    Optional<EmployeeSummary> findByEmployeeId(Long employeeId);

    @Modifying
    @Query("UPDATE EmployeeEntity e SET e.managerId = :managerId WHERE e.employeeId = :employeeId")
    int updateManager(Long employeeId, int managerId);

    void deleteByEmployeeId(Long employeeId);
}
