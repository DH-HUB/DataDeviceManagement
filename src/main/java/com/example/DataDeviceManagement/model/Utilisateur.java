package com.example.DataDeviceManagement.model;

import com.example.DataDeviceManagement.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Utilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueReservation.class)
    private Integer id = null;

    @JsonView(VueReservation.class)
    private String nom;

    @JsonView(VueReservation.class)
    private String prenom;

    //@JsonIgnore
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
    private String motDePasse;
    // num permettant d'invalidité
    private int numeroToken;


    private String email;

//    @JsonView(VueReservation.class)
//    private boolean admin;

    @ManyToMany
    @JoinTable(
            name="role_utilisateur",
            joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> listeRole = new ArrayList<>();

}
