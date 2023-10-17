package com.projetolombock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetolombock.entities.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

}
