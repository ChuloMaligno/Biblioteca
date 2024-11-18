package com.mongo.Biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.Biblioteca.model.Bibliotecario;

@Repository
public interface BibliotecarioRepository extends MongoRepository<Bibliotecario, String>{

	public Bibliotecario findByUsuario(String usuario);
}
