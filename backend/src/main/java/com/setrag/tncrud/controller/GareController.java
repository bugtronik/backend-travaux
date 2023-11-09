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

import com.setrag.tncrud.model.Gare;
import com.setrag.tncrud.repository.GareRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GareController {

	@Autowired
	private GareRepository gareRepository;
	
	@GetMapping("/gares")
	public ResponseEntity<List<Gare>> getAllGare() {
		try {
			List<Gare> gares = new ArrayList<Gare>();
			
			gareRepository.findAll().forEach(gares::add);
			
			return new ResponseEntity<>(gares, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/gares/{id}")
	public ResponseEntity<Gare> getGareById(@PathVariable("id") long id) {
		Optional<Gare> gareData = gareRepository.findById(id);
		
		if(gareData.isPresent()) {
			return new ResponseEntity<>(gareData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/gares")
	public ResponseEntity<Gare> createGare(@RequestBody Gare gare) {
		try {
			Gare _gare = gareRepository.save(new Gare(gare.getLibelle()));
			return new ResponseEntity<>(_gare, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/gares/{id}")
	public ResponseEntity<Gare> updateGare(@PathVariable("id") long id, @RequestBody Gare gare) {
		Optional<Gare> gareData = gareRepository.findById(id);
		
		if(gareData.isPresent()) {
			Gare _gare = gareData.get();
			_gare.setLibelle(gare.getLibelle());
			return new ResponseEntity<>(gareRepository.save(_gare), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/gares/{id}")
	public ResponseEntity<HttpStatus> deleteGare(@PathVariable("id") long id) {
		try {
			gareRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/gares")
	public ResponseEntity<HttpStatus> deleteAllGare() {
		try {
			gareRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
