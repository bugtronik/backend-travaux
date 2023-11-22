package com.setrag.tncrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	@Column(name="date_creation")
	private String date_creation;
	
	@Column(name="canton")
	private String canton;
	
	@Column(name="regime")
	private String regime;
	
	@Column(name="etat")
	private String etat;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_gare")
	private Gare gare;
	
	public Travaux() {
		
	}
	
	public Travaux(String demande_debut, 
				   String demande_fin,
				   String parcours,
				   String type,
				   String heure_debut,
				   String heure_fin,
				   String fin_reel,
				   String commentaire,
				   String date_creation,
				   String canton,
				   String regime,
				   String etat,
				   Gare gare) {
		this.demande_debut = demande_debut;
		this.demande_fin = demande_fin;
		this.parcours = parcours;
		this.type = type;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.fin_reel = fin_reel;
		this.commentaire = commentaire;
		this.date_creation = date_creation;
		this.canton = canton;
		this.regime = regime;
		this.etat = etat;
		this.gare = gare;
	}
}
