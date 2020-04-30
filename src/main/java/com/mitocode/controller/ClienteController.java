package com.mitocode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;

import com.mitocode.model.Cliente;
import com.mitocode.model.Usuario;
import com.mitocode.service.IClienteService;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@Autowired
	IUsuarioService iUsuarioService;

	/*@PostMapping
	public Cliente registrar(@RequestBody Cliente obj) {
		return service.registrar(obj);
	}

	@PutMapping
	public Cliente modificar(@RequestBody Cliente obj) {
		return service.modificar(obj);
	}*/

	@PostMapping
	public Cliente registrar(@RequestPart("cliente") Cliente cliente, @RequestPart("file") MultipartFile file)
			throws IOException {
		Cliente c = cliente;		
		c.setFoto(file.getBytes());		
		return service.registrar(c);
	}

	@PutMapping
	public Cliente modificar(@RequestPart("cliente") Cliente cliente, @RequestPart("file") MultipartFile file)
			throws IOException {
		Cliente c = cliente;
		c.setFoto(file.getBytes());
		return service.modificar(c);
	}

	/*@GetMapping("/{id}")
	public Cliente listarPorId(@PathVariable("id") Integer id) {
		return service.listarPorId(id);
	}*/

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> listarPorId(@PathVariable("id") Integer id) {
		Cliente c = service.listarPorId(id);
		byte[] data = c.getFoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/me", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> me() {
		Usuario u = iUsuarioService.getUsuario();
		Cliente c = service.listarPorId(u.getCliente().getIdCliente());
		byte[] data = c.getFoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@GetMapping
	public List<Cliente> listar() {
		return service.listar();
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
}
