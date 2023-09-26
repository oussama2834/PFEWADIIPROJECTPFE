package net.gestionachat.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gestionachat.entities.Commande;




@AllArgsConstructor @NoArgsConstructor
@Data
public class CommandeDto {

	private Integer idCmd;
	private Date dateCmd;
	private int delaiCmd;
	private String modePaiment;
	private String statut;
	private Double montantToltal;
	 private String adresseLivraison;


	 public static CommandeDto FromEntity(Commande entity) {

		 CommandeDto commandeDto =new CommandeDto();
		 BeanUtils.copyProperties(entity,commandeDto);
		  return commandeDto;

		  } public static Commande toEntity(CommandeDto dto) {
			  Commande commande=new Commande();
		  BeanUtils.copyProperties(dto,commande);
		  return commande;

		  }
}
