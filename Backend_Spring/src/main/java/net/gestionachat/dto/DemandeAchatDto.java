package net.gestionachat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.gestionachat.entities.Articles;
import net.gestionachat.entities.DemandeAchat;

import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @NoArgsConstructor
@Data
public class DemandeAchatDto {

    private Integer id;

    private Date dateDemande;
    private Date dateApprobation;
    private double qteDemandee;
    private double  qteApprouvee;
    private String description;
    private String delais;
    private String etat;
    private String motifRejet;
    private UserDto userDemandeur;
    private UserDto userApprouvant;
    private List<ArticleDto> articles ;

//    public static DemandeAchatDto FromEntity(DemandeAchat entity) {
//
//        DemandeAchatDto demandeAchatDto =new DemandeAchatDto();
//        BeanUtils.copyProperties(entity,demandeAchatDto);
//        return demandeAchatDto;
//
//    }
public static DemandeAchatDto FromEntity(DemandeAchat entity) {
    DemandeAchatDto demandeAchatDto = new DemandeAchatDto();
    BeanUtils.copyProperties(entity, demandeAchatDto);

    List<ArticleDto> articleDtos = entity.getArticles().stream()
            .map(ArticleDto::FromEntity)
            .collect(Collectors.toList());
      demandeAchatDto.setArticles(articleDtos);

    return demandeAchatDto;
}
//    public static DemandeAchat toEntity(DemandeAchatDto dto) {
//        DemandeAchat demandeAchat=new DemandeAchat();
//        BeanUtils.copyProperties(dto,demandeAchat);
//        return demandeAchat;
//
//    }
        public static DemandeAchat toEntity(DemandeAchatDto dto) {
        DemandeAchat demandeAchat=new DemandeAchat();
        BeanUtils.copyProperties(dto,demandeAchat);
            List<Articles> articles = dto.getArticles().stream()
                    .map(ArticleDto::toEntity)
                    .collect(Collectors.toList());
            demandeAchat.setArticles(articles);
        return demandeAchat;

    }
}
