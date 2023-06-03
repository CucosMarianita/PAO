package Service;

import Service.Persistence.CRUD_Template;
import entities.Sculptura;
import Interfaces.SculpturaInterface;
import Service.Persistence.Conn;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SculpturaService implements SculpturaInterface, CRUD_Template<Sculptura> {

    private List<Sculptura> sculpturi = new ArrayList<>();
    private Conn connection;
    private static SculpturaService instance;

    private SculpturaService(){
        connection = new Conn();
        sculpturi = findAll();    // (citire din db)
    }

    public static SculpturaService getInstance(){
        if(instance == null){
            instance = new SculpturaService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Sculptura s : sculpturi) {
            if (s.getID_exponat() > max) {
                max = s.getID_exponat();
            }
        }
        return max;
    }


    @Override
    public Sculptura readSculptura() {
        Scanner scanner = new Scanner(System.in);
        Sculptura sculptura = new Sculptura();

        int id = getMaxId() + 1;
        sculptura.setID_exponat(id);

        System.out.println("Denumire: ");
        sculptura.setDenumire(scanner.nextLine());

        System.out.println("An: ");
        while (true) {
            try {
                sculptura.setAn(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Anul trebuie sa fie un numar!");
            }
        }

        System.out.println("Stil: ");
        sculptura.setStil(scanner.nextLine());

        System.out.println("Material: ");
        sculptura.setMaterial(scanner.nextLine());

        System.out.println("Descriere: ");
        sculptura.setDescriere(scanner.nextLine());

        System.out.println("ID_galerie: ");
        while (true) {
            try {
                sculptura.setID_galerie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul galeriei trebuie sa fie un numar!");
            }
        }

        return sculptura;
    }

    @Override
    public List<Sculptura> getSculpturi() {
        List<Sculptura> sculpturiCopy = new ArrayList<>();
        sculpturiCopy.addAll(this.sculpturi);
        // sortare dupa stil si id
        sculpturiCopy.sort(new Comparator<Sculptura>() {
            @Override
            public int compare(Sculptura o1, Sculptura o2) {
                if(o1.getStil().compareTo(o2.getStil()) == 0){
                    return o1.getID_exponat() - o2.getID_exponat();
                }
                return o1.getStil().compareTo(o2.getStil());
            }
        });
        return sculpturiCopy;
    }

    @Override
    public Sculptura getSculpturaById(int index) {
        Sculptura sculptura = new Sculptura();
        for (Sculptura s : sculpturi) {
            if (s.getID_exponat() == index) {
                sculptura = s;
            }
        }
        return sculptura;
    }


    @Override
    public void add(Sculptura obj) throws SQLException {
        this.sculpturi.add(obj);
        connection.getS().execute("INSERT INTO sculptura(ID_exponat, denumire, an, stil, material, descriere, ID_galerie) VALUES "+
                        "(" + obj.getID_exponat() + ", '" + obj.getDenumire() + "', " + obj.getAn() + ", '" + obj.getStil() + "', '" + obj.getMaterial() + "', '" + obj.getDescriere() + "', " + obj.getID_galerie() + ")");
    }

    @Override
    public List<Sculptura> findAll() {
        try{
            String sql = "SELECT * FROM sculptura";
            ResultSet rs = connection.getS().executeQuery(sql);
            List<Sculptura> sculpturi = new ArrayList<>();

            while (rs.next()){
                Sculptura sculptura = new Sculptura();
                sculptura.setID_exponat(rs.getInt("ID_exponat"));
                sculptura.setDenumire(rs.getString("denumire"));
                sculptura.setAn(rs.getInt("an"));
                sculptura.setStil(rs.getString("stil"));
                sculptura.setMaterial(rs.getString("material"));
                sculptura.setDescriere(rs.getString("descriere"));
                sculptura.setID_galerie(rs.getInt("ID_galerie"));
                sculpturi.add(sculptura);
            }
            rs.close();

            return sculpturi;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Sculptura obj) {
        this.sculpturi.set(obj.getID_exponat()-1, obj);
        try{
            connection.getS().execute("UPDATE sculptura SET denumire = '" + obj.getDenumire() + "', an = " + obj.getAn() + ", stil = '"
                    + obj.getStil() + "', material = '" + obj.getMaterial() + "', descriere = '" + obj.getDescriere() + "', ID_galerie = " + obj.getID_galerie()
                    + " WHERE ID_exponat = " + obj.getID_exponat());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < this.sculpturi.size(); i++) {
            if (this.sculpturi.get(i).getID_exponat() == index) {
                this.sculpturi.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM sculptura WHERE ID_exponat = " + index);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
