package com.employee.managment.demo.repository;

import com.employee.managment.demo.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Integer> {

    List<ManagerEntity> findAll();

    Optional<ManagerEntity> findById(int managerId);

    @Modifying
    @Query("UPDATE ManagerEntity m SET m.status = :status WHERE m.managerId = :managerId")
    int updateManagerStatus(boolean status, int managerId);
}
