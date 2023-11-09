package com.setrag.tncrud.model;

import java.time.LocalTime;
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
public class Travaux {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "demande_debut")
	private Date demande_debut;
	
	@Column(name = "demande_fin")
	private Date demande_fin;
	
	@Column(name = "parcours")
	private String parcours;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "heure_debut")
	private Date heure_debut;
	
	@Column(name = "heure_fin")
	private Date heure_fin;
	
	@Column(name = "fin_reel")
	private Date fin_reel;
	
	@Column(name = "commentaire")
	private String commentaire;
	
	@Column(name="date_creation")
	private Date date_creation;
	
	@ManyToOne
	private Gare gare;
	
	@ManyToOne
	private Canton canton;
	
	@ManyToOne
	private Regime regime;
	
}
