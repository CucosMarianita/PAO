package Service;

import Interfaces.VizitatorInterface;
import entities.Vizitator;

import java.util.List;


public class VizitatorService implements VizitatorInterface {

    @Override
    public Vizitator readVizitator() {
        return null;
    }

    @Override
    public List<Vizitator> getVizitatori() {
        return null;
    }

    @Override
    public Vizitator getVizitatorById(int index) {
        return null;
    }

    @Override
    public void addVizitator(Vizitator vizitator) {
        System.out.println("Adaugare Vizitator");
    }

    @Override
    public void updateVizitator(int index, Vizitator vizitator) {
        System.out.println("Update Vizitator");
    }

    @Override
    public void deleteVizitatorById(int index) {
        System.out.println("Stergere Vizitator");
    }

    @Override
    public void deleteVizitator(Vizitator vizitator) {
        System.out.println("Stergere Vizitator");
    }
}


