package com.mongo.Biblioteca.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mongo.Biblioteca.exception.NotFoundException;
import com.mongo.Biblioteca.model.Libro;
import com.mongo.Biblioteca.repository.LibroRepository;

@Controller
public class LibroController {

	@Autowired
	LibroRepository repository;
	
	@GetMapping("/libros")
	public String librosList(Model model) {
		List<Libro> libros = repository.findAll();
		model.addAttribute("libros", libros);
		return "librosList";
	}
	
	@GetMapping("/libros/new")
	public String librosNew(Model model) {
		model.addAttribute("libro", new Libro());
		model.addAttribute("titulo", "Agregar Libro");
		return "librosForm";
	}
	
	@PostMapping("/libros/save")
	public String librosSave(@ModelAttribute("libros") Libro libro) {
		if(libro.getId().isEmpty()) {
			libro.setId(null);
		}
		try {
			
		
		if (!libro.getArchivoFoto().isEmpty() && libro.getArchivoFoto() != null) {
            libro.setImagen(libro.getArchivoFoto().getBytes());
        }
		}catch (IOException e) {
        e.printStackTrace();
        return "error"; // Página de error personalizada si ocurre un fallo
    }
		
		repository.save(libro);
		return "redirect:/libros";
		
	}
	
	@GetMapping("/libros/edit/{id}")
	public String librosEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("libro", repository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado")));
		model.addAttribute("titulo", "Editar Libro");
		return "librosForm";
	}
	
	@GetMapping("libros/delete/{id}")
	public String librosDelete(@PathVariable("id") String id) {
		repository.findById(id).orElseThrow(() -> new NotFoundException("Libro no encontrado"));
		repository.deleteById(id);
		return "redirect:/libros";
	}
}
