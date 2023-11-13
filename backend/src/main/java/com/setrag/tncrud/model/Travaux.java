package com.setrag.tncrud.model;
import java.util.Date;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_gare")
	private Gare gare;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_canton")
	private Canton canton;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_regime")
	private Regime regime;
}
