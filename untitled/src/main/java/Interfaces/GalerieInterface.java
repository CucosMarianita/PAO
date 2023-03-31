package Interfaces;

import entities.Galerie;

import java.util.List;

public interface GalerieInterface {

    public Galerie readGalerie();


    public List<Galerie> getGalerii();

    public Galerie getGalerieById(int index);


    // CRUD
    public void addGalerie(Galerie galerie);

    public void updateGalerie(int index, Galerie galerie);

    public void deleteGalerieById(int index);

    public void deleteGalerie(Galerie galerie);
}
