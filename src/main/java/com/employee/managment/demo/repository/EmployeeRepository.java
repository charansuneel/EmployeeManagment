package com.employee.managment.demo.repository;

import com.employee.managment.demo.EmployeeDto;
import com.employee.managment.demo.EmployeeSummary;
import com.employee.managment.demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity,Long>{

    @Query(value = "select first_name,date_of_birth from employee where employee_id =:id",nativeQuery = true)
    Optional<EmployeeDto> getEmployeesdetails(Long id);

    Optional<EmployeeSummary> findByEmployeeId(Long employeeId);

}
