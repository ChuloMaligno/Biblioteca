package com.mongo.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongo.Biblioteca.model.Administrador;
import com.mongo.Biblioteca.model.Bibliotecario;
import com.mongo.Biblioteca.model.Usuario;
import com.mongo.Biblioteca.repository.AdministradorRepository;
import com.mongo.Biblioteca.repository.BibliotecarioRepository;
import com.mongo.Biblioteca.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	AdministradorRepository aRepository;
	@Autowired
	BibliotecarioRepository bRepository;
	@Autowired
	UsuarioRepository uRepository;

	@GetMapping("/login/{tipo}")
	public String login(@PathVariable("tipo") String tipo, Model model) {
		model.addAttribute("tipo", tipo);
		return "login";
	}

	@PostMapping("/login/admin")
	public String loginAdmin(@RequestParam("usuario") String usuario, @RequestParam("contraseña") String contraseña,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Administrador admin = aRepository.findByUsuario(usuario);

		if (admin != null && admin.getContraseña().equals(contraseña)) {
			session.setAttribute("usuario", admin);
			return "redirect:/admin/vista";
		} else {
			redirectAttributes.addFlashAttribute("error", "Usuario o contraseña invalida");
			return "redirect:/login/administrador";
		}
	}

	@PostMapping("/login/bibliotecario")
	public String loginBibliotecario(@RequestParam("usuario") String usuario,
			@RequestParam("contraseña") String contraseña, HttpSession session, RedirectAttributes redirectAttributes) {
		Bibliotecario bibliotecario = bRepository.findByUsuario(usuario);

		if (bibliotecario != null && bibliotecario.getContraseña().equals(contraseña)) {
			session.setAttribute("usuario", bibliotecario);
			return "redirect:/bibliotecarios/vista";
		} else {
			redirectAttributes.addFlashAttribute("error", "Usuario o contraseña invalida");
			return "redirect:/login/bibliotecario";
		}
	}

	@PostMapping("/login/usuario")
	public String loginUsuario(@RequestParam("usuario") String usuario, @RequestParam("contraseña") String contraseña,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Usuario user = uRepository.findByUsuario(usuario);

		if (user != null && user.getContraseña().equals(contraseña)) {
			session.setAttribute("usuario", user);
			return "redirect:/usuarios/vista";
		} else {
			redirectAttributes.addFlashAttribute("error", "Usuario o contraseña invalida");
			return "redirect:/login/usuario";
		}
	}

	@GetMapping("/login/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
