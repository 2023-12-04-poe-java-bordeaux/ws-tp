package com.demo.api.dao;

import com.demo.api.modele.Spectacle;

import java.util.HashMap;
import java.util.List;

public class SpectacleDAO {

    /**** SINGLETON */
    private static SpectacleDAO instance;

    public static SpectacleDAO getInstance(){
        if(instance == null)
            instance = new SpectacleDAO();

        return instance;
    }

    private SpectacleDAO(){

    }
    /**** SINGLETON */

    private int idCount = 0;
    private HashMap<Integer, Spectacle> spectacles = new HashMap<>();

    public List<Spectacle> getSpectacles(){
        return spectacles.values().stream().toList();
    }

    public void addSpectacle(Spectacle s){
        idCount++;
        s.setId(idCount);
        spectacles.put(idCount, s);
    }


    public Spectacle getById(Integer spectacleId) {
        return spectacles.get(spectacleId);
    }
}
