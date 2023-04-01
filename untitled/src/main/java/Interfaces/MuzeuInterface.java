package Interfaces;

import entities.Muzeu;

public interface MuzeuInterface {

    public Muzeu readMuzeu(); //throws ParseException;


    public Muzeu getMuzeu();


    public void updateMuzeu(int index, Muzeu muzeu);

    public void addMuzeu(Muzeu muzeu);

    public void deleteMuzeu(Muzeu muzeu);

}
