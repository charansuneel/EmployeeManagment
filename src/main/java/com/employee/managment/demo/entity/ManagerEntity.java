package com.employee.managment.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "manager")
public class ManagerEntity {

    @Id
    @Column(name="id")
    private Long managerId;

    @Column(name="name")
    private String managerName;

    @Column(name="email")
    private String managerEmail;

    @Column(name="status")
    private Boolean status;
}
