package net.gestionachat.entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.gestionachat.user.User;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter

public class Fournisseur{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
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
        private boolean valide;
}
