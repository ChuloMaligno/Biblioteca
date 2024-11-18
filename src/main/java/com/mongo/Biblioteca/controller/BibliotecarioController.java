package com.mongo.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mongo.Biblioteca.exception.NotFoundException;
import com.mongo.Biblioteca.model.Bibliotecario;
import com.mongo.Biblioteca.repository.BibliotecarioRepository;

@Controller
public class BibliotecarioController {

	@Autowired
	BibliotecarioRepository repository;
	
	@GetMapping("/bibliotecarios/vista")
	public String vistaBibliotecario() {
		return "bibliotecarioView";
	}
	
	@GetMapping("/bibliotecarios")
	public String bibliotecarioList(Model model) {
		List<Bibliotecario> bibliotecarios = repository.findAll();
		model.addAttribute("bibliotecarios", bibliotecarios);
		return "bibliotecariosList";
	}
	
	@GetMapping("/bibliotecarios/new")
	public String bibliotecarioNew(Model model) {
		model.addAttribute("bibliotecario", new Bibliotecario());
		model.addAttribute("titulo", "Agregar bibliotecario");
		return "bibliotecariosForm";
	}
	
	@PostMapping("/bibliotecarios/save")
	public String bibliotecarioSave(@ModelAttribute("bibliotecarios") Bibliotecario bibliotecario) {
		if(bibliotecario.getId().isEmpty()) {
			bibliotecario.setId(null);
		}
		
		repository.save(bibliotecario);
		return "redirect:/bibliotecarios";
	}
	
	@GetMapping("/bibliotecarios/edit/{id}")
	public String bibliotecarioEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("bibliotecario", repository.findById(id).orElseThrow(() -> new NotFoundException("Bbiliotecario no encontrado")));
		model.addAttribute("titulo", "Editar bibliotecario");
		return "bibliotecariosForm";
	}
	
	@GetMapping("/bibliotecarios/delete/{id}")
	public String bibliotecariosDelete(@PathVariable("id") String id) {
		repository.findById(id).orElseThrow(() -> new NotFoundException("Bibliotecario no encontrado"));
		repository.deleteById(id);
		return "redirect:/bibliotecarios";
	}
}
