package com.example.DataDeviceManagement.dao;

import com.example.DataDeviceManagement.model.CleReservation;
import com.example.DataDeviceManagement.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, CleReservation> {

    //SELECT *
    // FROM emprunteur
    // WHERE emprunter_id = :EmprunteurId
    // AND materiel_id = :MaterielId
    // AND date = :Date
    Optional<Reservation> findByEmprunteurIdAndMaterielIdAndDate(
            Integer emprunteurId,
            Integer materielId,
            Date date
    );

    void deleteByEmprunteurIdAndMaterielIdAndDate(
            Integer emprunteurId,
            Integer materielId,
            Date date
    );

}