package Interfaces;

import entities.Bilet;

import java.util.List;

public interface BiletInterface {

    public Bilet readBilet();

    public List<Bilet> getBilete();

    public List<Bilet> getBiletePerioada(int nr_luni);

    public Bilet getBiletById(int index);


}
