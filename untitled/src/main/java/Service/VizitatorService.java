package Service;

import Interfaces.VizitatorInterface;
import entities.Vizitator;

import java.util.*;


public class VizitatorService implements VizitatorInterface {

    private static VizitatorService instance;
    private final List<Vizitator> vizitatori = new ArrayList<>();


    public static VizitatorService getInstance(){
        if(instance == null){
            instance = new VizitatorService();
        }
        return instance;
    }

    @Override
    public Vizitator readVizitator() {
        Scanner scanner = new Scanner(System.in);
        Vizitator vizitator = new Vizitator();

        System.out.println("Nume: ");
        vizitator.setNume(scanner.nextLine());

        System.out.println("Prenume: ");
        vizitator.setPrenume(scanner.nextLine());

        System.out.println("Telefon: ");
        vizitator.setTelefon(scanner.nextLine());

        System.out.println("Mail: ");
        vizitator.setMail(scanner.nextLine());

        return vizitator;
    }

    @Override
    public List<Vizitator> getVizitatori() {
        List<Vizitator> vizitatoriCopy = new ArrayList<>();
        vizitatoriCopy.addAll(vizitatori);
        // sortare dupa nume si prenume
        vizitatoriCopy.sort(Comparator.comparing(Vizitator::getNume).thenComparing(Vizitator::getPrenume));
        return vizitatoriCopy;
    }

    @Override
    public void addVizitator(Vizitator vizitator) {
        this.vizitatori.add(vizitator);
    }

}


