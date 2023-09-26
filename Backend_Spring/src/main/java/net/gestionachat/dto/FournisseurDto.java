package net.gestionachat.dto;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.gestionachat.entities.Fournisseur;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class FournisseurDto {

     private Integer id;
	private String username;
	private String name;
	private String password;
	private String email;
	private int tele;
	private String address;
	private String sexe;
	private String position;
	private String departement;
	private Boolean valide;
	 
	  public static FournisseurDto fromEntity(Fournisseur entity) {

		  FournisseurDto fournisseurDto =new FournisseurDto();
		        BeanUtils.copyProperties(entity,fournisseurDto);
		        return fournisseurDto;

		    }

		    public static Fournisseur toEntity(FournisseurDto dto) {

		    	Fournisseur fournisseur=new Fournisseur();
		        BeanUtils.copyProperties(dto,fournisseur);
		        return fournisseur;

		    }


}
