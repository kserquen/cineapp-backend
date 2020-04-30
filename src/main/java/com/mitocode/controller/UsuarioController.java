package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	IUsuarioService iUsuarioService;
	
	@GetMapping("/me")
	public Usuario me() {
		return iUsuarioService.getUsuario();
	}
}
