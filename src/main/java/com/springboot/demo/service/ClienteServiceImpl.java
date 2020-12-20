package com.springboot.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.springboot.demo.dao.IClienteDao;
import com.springboot.demo.entity.Cliente;

public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public List<Cliente> findAll() {
		return  (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente findById(Long id) {

		return clienteDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("No se encontro el ID: "+id));
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {

		clienteDao.deleteById(id);
	}

}
