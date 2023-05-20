package Interfaces;

import entities.Expozitie;

import java.util.List;

public interface ExpozitieInterface {

    public Expozitie readExpozitie();


    public List<Expozitie> getExpozitii();

    public Expozitie getExpozitieById(int index);


}
