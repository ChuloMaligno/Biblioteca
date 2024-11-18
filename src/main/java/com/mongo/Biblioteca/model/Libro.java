package com.mongo.Biblioteca.model;

import java.util.Base64;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "libros")
public class Libro {

	@Id
	private String id;
	
	private String nombre;
	
	private String autor;
	
	private String fecha;
	
	private byte[] imagen;
	
	private int cantidad;
	
	@Transient
    private MultipartFile archivoFoto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public String getImagenBase64() {
        return imagen != null ? Base64.getEncoder().encodeToString(imagen) : null;
    }

	public MultipartFile getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(MultipartFile archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
