package com.setrag.tncrud.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "travaux")
@Data
public class travaux {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "demande_debut")
	private String demande_debut;
	
	@Column(name = "demande_fin")
	private String demande_fin;
	
	@Column(name = "parcours")
	private String parcours;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "heure_debut")
	private String heure_debut;
	
	@Column(name = "heure_fin")
	private String heure_fin;
	
	@Column(name = "fin_reel")
	private String fin_reel;
	
	@Column(name = "commentaire")
	private String commentaire;
	
	private Date date_creation;
	
	@ManyToOne
	private Gare gare;
	
	@ManyToOne
	private Canton canton;
	
	@ManyToOne
	private Regime regime;
	
	
}
