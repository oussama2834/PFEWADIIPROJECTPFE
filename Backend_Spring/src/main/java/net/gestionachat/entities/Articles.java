package net.gestionachat.entities;

import java.util.Date;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Articles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double price;
	private Integer stock;
	private String description;
	private int quantity;
	private double taxPercent;
	private String brand;
	private String reserved;
	private String cost;
	private  String vendor;
	private Date dateAdded;
	private Boolean active;


//	@ManyToOne
//	@JoinColumn(name = "demande_achat_id")
//	private DemandeAchat demandeAchat;

}
