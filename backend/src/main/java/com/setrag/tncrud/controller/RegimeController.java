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

import com.setrag.tncrud.model.Regime;
import com.setrag.tncrud.repository.RegimeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RegimeController {

	@Autowired
	private RegimeRepository regimeRepository;
	
	@GetMapping("/regimes")
	public ResponseEntity<List<Regime>> getAllregimes() {
		try {
			List<Regime> regimes = new ArrayList<Regime>();
			regimeRepository.findAll().forEach(regimes::add);
			
			if(regimes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(regimes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/regimes/{id}")
	public ResponseEntity<Regime> getRegimeById(@PathVariable("id") long id) {
		Optional<Regime> regimeData = regimeRepository.findById(id);
		
		if(regimeData.isPresent()) {
			return new ResponseEntity<>(regimeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/regimes")
	public ResponseEntity<Regime> createRegime(@RequestBody Regime regime) {
		try {
			Regime _regime = regimeRepository.save(new Regime(regime.getLibelle()));
			return new ResponseEntity<>(_regime, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/regimes/{id}")
	public ResponseEntity<Regime> updateRegime(@PathVariable("id") long id, @RequestBody Regime regime) {
		Optional<Regime> regimeData = regimeRepository.findById(id);
		
		if(regimeData.isPresent()) {
			Regime _regime = regimeData.get();
			_regime.setLibelle(regime.getLibelle());
			return new ResponseEntity<>(regimeRepository.save(_regime), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/regimes/{id}")
	public ResponseEntity<HttpStatus> deleteRegime(@PathVariable("id") long id) {
		try {
			regimeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/regimes")
	public ResponseEntity<HttpStatus> deleteAllRegimes() {
		try {
			regimeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
