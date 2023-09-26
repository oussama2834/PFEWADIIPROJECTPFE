package net.gestionachat.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCmd;
	private Date dateCmd;
	private int delaiCmd;
	private String modePaiment;
	private String statut;
	private Double montantToltal;
	 private String adresseLivraison;




}
