package com.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
