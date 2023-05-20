package Interfaces;

import entities.Galerie;

import java.util.List;

public interface GalerieInterface {

    public Galerie readGalerie();


    public List<Galerie> getGalerii();

    public Galerie getGalerieById(int index);


}
