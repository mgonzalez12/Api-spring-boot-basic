package com.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.entity.Employee;

public interface IEmployeeDao extends CrudRepository<Employee, Long> {

}
