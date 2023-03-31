package Interfaces;

import entities.Vizitator;

import java.util.List;

public interface VizitatorInterface {

    public Vizitator readVizitator();


    public List<Vizitator> getVizitatori();

    public Vizitator getVizitatorById(int index);


    // CRUD
    public void addVizitator(Vizitator vizitator);

    public void updateVizitator(int index, Vizitator vizitator);

    public void deleteVizitatorById(int index);

    public void deleteVizitator(Vizitator vizitator);

}




