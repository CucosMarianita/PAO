package Service;

import Interfaces.GalerieInterface;
import Service.Persistence.CRUD_Template;
import entities.Galerie;
import Service.Persistence.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GalerieService implements GalerieInterface, CRUD_Template<Galerie> {

    private List<Galerie> galerii = new ArrayList<>();
    private Conn connection;
    private static GalerieService instance;


    private GalerieService(){
        connection = new Conn();
        galerii = findAll();    // (citire din db)
    }

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
    public void add(Galerie obj) throws SQLException {
        this.galerii.add(obj);
        connection.getS().execute("INSERT INTO galerie (ID_galerie, nume, ID_expozitie)"+
                                " VALUES (" + obj.getID_galerie() + ", '" + obj.getNume() + "', " + obj.getID_expozitie() + ")");
    }

    @Override
    public List<Galerie> findAll() {
        try{
            String select = "SELECT * FROM galerie";
            ResultSet rs =  connection.getS().executeQuery(select);

            List<Galerie> galerii = new ArrayList<>();

            while(rs.next()){
                Galerie galerie = new Galerie();
                galerie.setID_galerie(rs.getInt("ID_galerie"));
                galerie.setNume(rs.getString("nume"));
                galerie.setID_expozitie(rs.getInt("ID_expozitie"));
                galerii.add(galerie);
            }
            rs.close();

            return galerii;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Galerie obj) {
        this.galerii.set(obj.getID_galerie()-1, obj);
        try{
            String update = "UPDATE galerie SET nume = '" + obj.getNume() + "', ID_expozitie = " + obj.getID_expozitie() +
                    " WHERE ID_galerie = " + obj.getID_galerie();
            connection.getS().execute(update);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for(int i = 0; i < this.galerii.size(); i++){
            if(this.galerii.get(i).getID_galerie() == index){
                this.galerii.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM galerie WHERE ID_galerie = " + index);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
