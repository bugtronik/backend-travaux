package com.setrag.tncrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.tncrud.model.Canton;
import com.setrag.tncrud.repository.CantonRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CantonController {
	
	@Autowired
	private CantonRepository cantonRepository;
	
	@GetMapping("/cantons")
	public ResponseEntity<List<Canton>> getAllCantons() {
		try {
			List<Canton> cantons = new ArrayList<Canton>();
			cantonRepository.findAll().forEach(cantons::add);
			
			if(cantons.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(cantons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/cantons")
	public ResponseEntity<Canton> createCanton(@RequestBody Canton canton) {
		try {
			Canton _canton = cantonRepository.save(new Canton(canton.getLibelle()));
			return new ResponseEntity<>(_canton, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantons/{id}")
	public ResponseEntity<Canton> getCantonById(@PathVariable("id") long id) {
		Optional<Canton> cantonData = cantonRepository.findById(id);
		
		if(cantonData.isPresent()) {
			return new ResponseEntity<>(cantonData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/cantons/{id}")
	public ResponseEntity<Canton> updateCanton(@PathVariable("id") long id, @RequestBody Canton canton) {
		Optional<Canton> cantonData = cantonRepository.findById(id);
		
		if(cantonData.isPresent()) {
			Canton _canton = cantonData.get();
			_canton.setLibelle(canton.getLibelle());
			return new ResponseEntity<>(cantonRepository.save(_canton), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/cantons/{id}")
	public ResponseEntity<HttpStatus> deleteCanton(@PathVariable("id") long id) {
		try  {
			cantonRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/cantons")
	public ResponseEntity<HttpStatus> deleteAllCantons() {
		try {
			cantonRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
