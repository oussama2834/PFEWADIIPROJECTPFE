package net.gestionachat.service.impl;



import lombok.RequiredArgsConstructor;
import net.gestionachat.Exception.EntityNotFoundException;
import net.gestionachat.Exception.ErrorCodes;
import net.gestionachat.Exception.InvalidOperationException;
import net.gestionachat.dto.DemandeAchatDto;

import net.gestionachat.dto.UserDto;
import net.gestionachat.entities.DemandeAchat;
import net.gestionachat.repository.DemandeAchatRep;
import net.gestionachat.service.interFace.DemandeAchatService;

import net.gestionachat.user.User;
import net.gestionachat.user.UserRepository;
import net.gestionachat.validator.ObjectValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DemandeAchatImplService implements DemandeAchatService {

    private final DemandeAchatRep demandeAchatRep;
    private final ObjectValidator<DemandeAchatDto> objectValidator;
    private final UserRepository userRepository ;

   @Override
    public DemandeAchatDto saveDemandeAchat(DemandeAchatDto dto ) {
        // get User
       // User demandeur = userRepository.findByEmail(authentication.getName()).get();
        objectValidator.validate(dto);
        dto.setEtat("Treatement");
        DemandeAchat demandeAchat = DemandeAchatDto.toEntity(dto);


       // demandeAchat.setUserDemandeur(demandeur);

        return DemandeAchatDto.FromEntity(
                demandeAchatRep.save(demandeAchat));
    }

    //methodes demandeAchat avec userDemandeur et userAprover
   /* @Override
    public DemandeAchatDto saveDemandeAchat(DemandeAchatDto dto, Authentication authentication, boolean isApprover) {
        // Récupérer l'utilisateur demandeur à partir de l'authentification
        User demandeur = getUserFromAuthentication(authentication);

        // Valider l'objet DTO avant de le convertir en entité
        objectValidator.validate(dto);

        // Convertir le DTO en entité DemandeAchat
        DemandeAchat demandeAchat = DemandeAchatDto.toEntity(dto);

        // Définir l'utilisateur demandeur pour la demande d'achat
        demandeAchat.setUserDemandeur(demandeur);

        // Enregistrer la demande d'achat et renvoyer le DTO résultant
        DemandeAchat savedDemandeAchat = demandeAchatRep.save(demandeAchat);
        return DemandeAchatDto.FromEntity(savedDemandeAchat);
    }
*/
   /* public User getUserFromAuthentication(Authentication authentication) {
        String userEmail = authentication.getName();
        Optional<User> demandeurOptional = userRepository.findByEmail(userEmail);

        if (demandeurOptional.isPresent()) {
            return demandeurOptional.get();
        } else {
            throw new IllegalArgumentException("Utilisateur demandeur non trouvé pour l'adresse email : " + userEmail);
        }
    }*/

   /* public DemandeAchatDto userApprover(DemandeAchatDto dto, Authentication authentication) {
        // Récupérer l'utilisateur approuvant à partir de l'authentification
        User approver = getUserFromAuthentication(authentication);

        // Valider l'objet DTO avant de le convertir en entité
        objectValidator.validate(dto);

        // Rechercher la demande d'achat en fonction de son ID (vous devez implémenter cette méthode dans votre demandeAchatRep)
        Optional<DemandeAchat> demandeAchatOptional = demandeAchatRep.findById(dto.getId());

        if (demandeAchatOptional.isPresent()) {
            DemandeAchat demandeAchat = demandeAchatOptional.get();

            // Définir l'utilisateur approuvant pour la demande d'achat
            demandeAchat.setUserApprouvant(approver);

            // Enregistrer la demande d'achat mise à jour et renvoyer le DTO résultant
            DemandeAchat savedDemandeAchat = demandeAchatRep.save(demandeAchat);
            return DemandeAchatDto.FromEntity(savedDemandeAchat);
        } else {
            throw new IllegalArgumentException("Demande d'achat non trouvée pour l'ID : " + dto.getId());
        }
    }
*/

    @Override
    public DemandeAchatDto findById(Long id) throws EntityNotFoundException {
        DemandeAchat demandeAchat = demandeAchatRep.findById(Math.toIntExact(id)).orElse(null);
        if (demandeAchat == null) {
            return null;
        }

        DemandeAchatDto demandeAchatDto = new DemandeAchatDto();
        BeanUtils.copyProperties(demandeAchat, demandeAchatDto);


        return demandeAchatDto;
    }

    @Override
    public void ValiderDemande(DemandeAchatDto dto) {
        DemandeAchat demandeAchat = DemandeAchatDto.toEntity(dto);
        User userApprouvant = UserDto.toEntity(dto.getUserApprouvant());
        demandeAchat.setUserApprouvant(userApprouvant);
            demandeAchat.setEtat("Accepted");
            demandeAchatRep.save(demandeAchat);

    }

    @Override
    public void RefuserDemande(DemandeAchatDto dto) {
        DemandeAchat demandeAchat = DemandeAchatDto.toEntity(dto);
        User userApprouvant = UserDto.toEntity(dto.getUserApprouvant());
        demandeAchat.setUserApprouvant(userApprouvant);
        demandeAchat.setEtat("Refused");
        demandeAchatRep.save(demandeAchat);
    }

    @Override
    public DemandeAchatDto updateDemandeAchat(DemandeAchatDto dto) throws EntityNotFoundException {
        // Valider l'objet DTO à l'aide de l'objectValidator (assurez-vous que cette étape est correcte)
        objectValidator.validate(dto);

        // Trouver l'utilisateur dans la base de données en utilisant l'ID du DTO
        DemandeAchat demandeAchat = demandeAchatRep.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Mettez à jour les propriétés de l'entité avec les valeurs du DTO
        demandeAchat.setDateDemande(dto.getDateDemande());
        demandeAchat.setDateApprobation(dto.getDateApprobation());
        demandeAchat.setEtat(dto.getEtat());
        demandeAchat.setDescription(dto.getDescription());
        demandeAchat.setDelais(dto.getDelais());
        demandeAchat.setMotifRejet(dto.getMotifRejet());
        demandeAchat.setQteApprouvee(dto.getQteApprouvee());
        demandeAchat.setQteDemandee(dto.getQteDemandee());
        // ... autres propriétés à mettre à jour

        // Enregistrez les modifications dans la base de données
        demandeAchat = demandeAchatRep.save(demandeAchat);

        // Retournez le DTO mis à jour
        return DemandeAchatDto.FromEntity(demandeAchat);
    }


    @Override
    public List<DemandeAchatDto> findAll() {
        return  demandeAchatRep.findAll().stream()
                .map(DemandeAchatDto ::FromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (id == null) {
            throw new InvalidOperationException("ID is NULL", ErrorCodes.DEMANDEACHA_ID_IS_NULL);
        }
        DemandeAchat admin = demandeAchatRep.findById(Math.toIntExact(id)).orElseThrow(()->new EntityNotFoundException(id+" not found",ErrorCodes.DEMANDEACHA_NOT_FOUND));
        demandeAchatRep.deleteById(Math.toIntExact(id));
    }
}
