package com.example.DataDeviceManagement.model;

import com.example.DataDeviceManagement.view.VueMarque;
import com.example.DataDeviceManagement.view.VueMateriel;
import com.example.DataDeviceManagement.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)

public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class })
    private Integer id;

    @Column(unique = true)
    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class})
    private String code;

    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class})
    private String nom;

    @ManyToOne
    @JsonView(VueMateriel.class)
    private Marque marque;

    @ManyToMany
    @JsonView(VueMateriel.class)
    @JoinTable(
            name = "materiel_specificite",
            joinColumns = @JoinColumn(name = "materiel_id"),
            inverseJoinColumns = @JoinColumn(name = "specificite_id"))
    private List<Specificite> listeSpecificite = new ArrayList<>();


}
