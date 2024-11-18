package com.mongo.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mongo.Biblioteca.repository.AdministradorRepository;

@Controller
public class AdminController {

	@Autowired
	AdministradorRepository repository;
	
	@GetMapping("admin/vista")
	public String vistaAdmin() {
		return "adminView";
	}
}
