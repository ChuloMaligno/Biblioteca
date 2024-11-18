package com.mongo.Biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.Biblioteca.model.Libro;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String>{

}
