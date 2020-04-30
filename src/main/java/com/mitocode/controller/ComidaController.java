package com.mitocode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.model.Comida;
import com.mitocode.service.IComidaService;

@RestController
@RequestMapping("/comidas")
public class ComidaController {

	@Autowired
	private IComidaService service;

	/*@PostMapping
	public Comida registrar(@Valid @RequestBody Comida c)
			throws IOException {			
		return service.registrar(c);
	}

	@PutMapping
	public Comida modificar(@Valid @RequestBody Comida c)
			throws IOException {		
		return service.modificar(c);
	}*/
	
	@PostMapping
	public Comida registrar(@RequestPart("comida") Comida comida, @RequestPart("file") MultipartFile file)
			throws IOException {
		Comida c = comida;		
		c.setFoto(file.getBytes());		
		return service.registrar(c);
	}

	@PutMapping
	public Comida modificar(@RequestPart("comida") Comida comida, @RequestPart("file") MultipartFile file)
			throws IOException {
		Comida c = comida;		
		c.setFoto(file.getBytes());
		return service.modificar(c);
	}

	/*@GetMapping("/{id}")
	public Comida listarPorId(@PathVariable("id") Integer id) {
		return service.listarPorId(id);
	}*/
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> listarPorId(@PathVariable("id") Integer id) {
		Comida c = service.listarPorId(id);
		byte[] data = c.getFoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@GetMapping
	public List<Comida> listar() {
		return service.listar();
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
}
