package com.demo.api.dao;

import com.demo.api.modele.Reservation;
import com.demo.api.modele.Spectacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationDAO {

    /**** SINGLETON */
    private static ReservationDAO instance;

    public static ReservationDAO getInstance(){
        if(instance == null)
            instance = new ReservationDAO();

        return instance;
    }

    private ReservationDAO(){

    }
    /**** SINGLETON */

    private int idCount = 0;
    private HashMap<Integer, Reservation> reservations = new HashMap<>();

    public List<Reservation> getReservations(){
        return reservations.values().stream().toList();
    }

    public void addReservation(Reservation s){
        idCount++;
        s.setId(idCount);
        reservations.put(idCount, s);
    }


    public Reservation getById(Integer id) {
        return reservations.get(id);
    }

    public void update(Integer id, Reservation r) {
        reservations.put(id, r);
    }

    public void deleteById(Integer id) {
        reservations.remove(id);
    }

    public List<Reservation> getBySpectacleId(Integer spectacleId){
        ArrayList<Reservation> resultat = new ArrayList<>();
        for (Reservation r : reservations.values()){
            if(r.getSpectacleId().equals(spectacleId))
                resultat.add(r);
        }
        return resultat;
    }
}
