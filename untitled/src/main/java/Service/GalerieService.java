package Service;

import Interfaces.GalerieInterface;
import entities.Galerie;

import java.util.*;

public class GalerieService implements GalerieInterface {

    private List<Galerie> galerii = new ArrayList<>();
    private static GalerieService instance;


    public static GalerieService getInstance(){
        if(instance == null){
            instance = new GalerieService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Galerie g : galerii) {
            if (g.getID_galerie() > max) {
                max = g.getID_galerie();
            }
        }
        return max;
    }

    @Override
    public Galerie readGalerie() {

        Scanner scanner = new Scanner(System.in);
        Galerie galerie = new Galerie();

        int id = getMaxId() + 1;
        galerie.setID_galerie(id);

        System.out.println("Nume: ");
        galerie.setNume(scanner.nextLine());

        System.out.println("ID_expozitie: ");
        while (true) {
            try {
                galerie.setID_expozitie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID_expozitie trebuie sa fie un numar!");
            }
        }

        return galerie;
    }

    @Override
    public List<Galerie> getGalerii() {
        List<Galerie> galeriiCopy = new ArrayList<>();
        galeriiCopy.addAll(this.galerii);
        // sortare dupa nume
        Collections.sort(galeriiCopy, new Comparator<Galerie>() {
            @Override
            public int compare(Galerie g1, Galerie g2) {
                return g1.getNume().compareTo(g2.getNume());
            }
        });
        return galeriiCopy;
    }

    @Override
    public Galerie getGalerieById(int index) {
        Galerie galerie = new Galerie();
        for (Galerie g : galerii) {
            if (g.getID_galerie() == index) {
                galerie = g;
            }
        }
        return galerie;
    }

    @Override
    public void addGalerie(Galerie galerie) {
        this.galerii.add(galerie);
    }

    @Override
    public void updateGalerie(int index, Galerie galerie) {
        for(int i = 0; i < this.galerii.size(); i++){
            if(this.galerii.get(i).getID_galerie() == index){
                this.galerii.remove(i);
                this.galerii.add(i, galerie);
                break;
            }
        }
    }

    @Override
    public void deleteGalerieById(int index) {
        for(int i = 0; i < this.galerii.size(); i++){
            if(this.galerii.get(i).getID_galerie() == index){
                this.galerii.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteGalerie(Galerie galerie) {
        this.galerii.remove(galerie);
    }
}
