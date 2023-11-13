package com.setrag.tncrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="regime")
@Data
public class Regime {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "libelle")
	private String libelle;
	
	public Regime() {
		
	}
	
	public Regime(String libelle) {
		this.libelle = libelle;
	}
}
