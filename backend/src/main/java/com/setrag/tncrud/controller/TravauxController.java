package com.setrag.tncrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.tncrud.model.Travaux;
import com.setrag.tncrud.repository.TravauxRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TravauxController {
	
	@Autowired
	private TravauxRepository travauxRepository;
	
	@GetMapping("/travaux")
	public ResponseEntity<List<Travaux>> getAllTravaux() {
		try {
			List<Travaux> travaux = new ArrayList<Travaux>();
			travauxRepository.findAll().forEach(travaux::add);
			
			if(travaux.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(travaux, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/travaux/{id}")
	public ResponseEntity<Travaux> getTravauxById(@PathVariable("id") long id) {
		Optional<Travaux> travauxData = travauxRepository.findById(id);
		
		if(travauxData.isPresent()) {
			return new ResponseEntity<>(travauxData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/travaux")
	public ResponseEntity<Travaux> createTravaux(@RequestBody Travaux travaux) {
		try {
			Travaux _travaux = travauxRepository.save(travaux);
			return new ResponseEntity<>(_travaux, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
