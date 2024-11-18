package com.mongo.Biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.Biblioteca.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

	public Usuario findByUsuario(String usuario);
}
