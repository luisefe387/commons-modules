package com.mytrading.modules.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mytrading.modules.commons.services.CommonService;

public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public  ResponseEntity<?> crear(@RequestBody E entity){
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb); 
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
//		Optional<Alumno> o = service.findById(id);
//		if(o.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		Alumno alumnoDb = o.get();
//		alumnoDb.setNombre(alumno.getNombre());
//		alumnoDb.setApellido(alumno.getApellido());
//		alumnoDb.setEmail(alumno.getEmail());
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb)); 
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
