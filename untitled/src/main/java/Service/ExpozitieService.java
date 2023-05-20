package Service;

import Interfaces.ExpozitieInterface;
import Service.Persistence.CRUD_Template;
import Service.Persistence.Conn;
import entities.Expozitie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ExpozitieService implements ExpozitieInterface, CRUD_Template<Expozitie> {

    private List<Expozitie> expozitii = new ArrayList<>();
    private Conn connection;
    private static ExpozitieService instance;

    private ExpozitieService(){
        connection = new Conn();
        expozitii = findAll();    // (citire din db)
    }

    public static ExpozitieService getInstance(){
        if(instance == null){
            instance = new ExpozitieService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Expozitie e : expozitii) {
            if (e.getID_expozitie() > max) {
                max = e.getID_expozitie();
            }
        }
        return max;
    }

    @Override
    public Expozitie readExpozitie() {

        Scanner scanner = new Scanner(System.in);
        Expozitie expozitie = new Expozitie();

        int id = getMaxId() + 1;
        expozitie.setID_expozitie(id);

        System.out.println("Descriere: ");
        expozitie.setDescriere(scanner.nextLine());

        System.out.println("ID_tur: ");
        while (true) {
            try {
                expozitie.setID_tur(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID_tur trebuie sa fie un numar!");
            }
        }

        return expozitie;
    }

    @Override
    public List<Expozitie> getExpozitii() {
        List<Expozitie> expoCopy = new ArrayList<>();
        expoCopy.addAll(this.expozitii);
        // sortare dupa id
        expoCopy.sort((o1, o2) -> o1.getID_expozitie() - o2.getID_expozitie());
        return expoCopy;
    }

    @Override
    public Expozitie getExpozitieById(int index) {
        Expozitie expozitie = new Expozitie();
        for (Expozitie e : expozitii) {
            if (e.getID_expozitie() == index) {
                expozitie = e;
            }
        }
        return expozitie;
    }


    @Override
    public List<Expozitie> findAll() {
        try{
            String select = "SELECT * FROM expozitie";
            ResultSet rs = connection.getS().executeQuery(select);

            List<Expozitie> expozitii = new ArrayList<>();

            while(rs.next()){
                Expozitie expozitie = new Expozitie();
                expozitie.setID_expozitie(rs.getInt("ID_expozitie"));
                expozitie.setDescriere(rs.getString("descriere"));
                expozitie.setID_tur(rs.getInt("ID_tur"));
                expozitii.add(expozitie);
            }
            rs.close();

            return expozitii;

        }catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void add(Expozitie obj) throws SQLException {
        this.expozitii.add(obj);

        String insert = "INSERT INTO expozitie (ID_expozitie, descriere, ID_tur) VALUES (" + obj.getID_expozitie() + ", " + obj.getDescriere() + ", " + obj.getID_tur() + ")";
        connection.getS().execute(insert);
    }

    @Override
    public void update(Expozitie obj) {
        this.expozitii.set(obj.getID_expozitie(), obj);
        try{
            connection.getS().execute("UPDATE expozitie SET descriere = '" + obj.getDescriere() + "', ID_tur = " + obj.getID_tur() +
                                    " WHERE ID_expozitie = " + obj.getID_expozitie() );

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < expozitii.size(); i++) {
            if (expozitii.get(i).getID_expozitie() == index) {
                expozitii.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM expozitie WHERE ID_expozitie = " + index);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
