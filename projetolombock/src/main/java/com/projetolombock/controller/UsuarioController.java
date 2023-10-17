package com.projetolombock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetolombock.entities.Usuario;
import com.projetolombock.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "usuario", description = "API REST DE GERECIAMENTO DE USUARIOS")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario ID")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario Usuario = usuarioService.getUsuarioById(id);
		if (Usuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuarios")
	public ResponseEntity<List<Usuario>> getAllUsuario() {
		List<Usuario> usuario = usuarioService.getAllUsuario();
		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um usuario")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
		Usuario criarUsuario = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarUsuario);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um usuario")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
		Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
		if (updatedUsuario != null) {
			return ResponseEntity.ok(updatedUsuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um usuario")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
		boolean deleted = usuarioService.deleteUsuario(id);
		if (deleted) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
