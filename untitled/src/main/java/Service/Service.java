package Service;

import entities.Angajat;

import java.text.ParseException;
import java.util.*;
import java.io.IOException;

public class Service {

    private final AngajatService service_angajat = AngajatService.getInstance();
    private static Service instance;
    private Scanner scanner = new Scanner(System.in);

    private Service(){}

    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    public void printUserMenu(){
        System.out.println(" 1 - Vizitator");
        System.out.println(" 2 - Angajat");
        System.out.println(" 0 - Exit");
    }

    public void meniu() throws IOException {
        while(true){
            printUserMenu();
            int option;
            while(true){
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if(option >= 0 && option <= 2) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 2");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 2");
                }
            }
            if (option == 0) {
                break;
            } else if (option == 1) {
                meniuVizitator();
            } else if (option == 2) {
                meniuAngajat();
            }
        }
    }

    public void printMeniuVizitator(){
        System.out.println();
        System.out.println(" 1 - Rezervare bilet");
        System.out.println(" 2 - Afisare program muzeu");
        System.out.println(" 3 - Afisare tururi");
        System.out.println(" 4 - Afisare expozitii");
        System.out.println(" 0 - Exit");

    }

    public void meniuVizitator(){
        while(true){
            printMeniuVizitator();
            int option;
            while(true){
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if(option >= 0 && option <=4) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 4");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 4");
                }
            }
            if(option == 1){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 2){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 3){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 4){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 0){
                break;
            }
        }
    }

    public void printMeniuAngajat(){
        System.out.println();
        System.out.println(" 1 - Afisare numar vizitatori");
        System.out.println(" 2 - Sortare exponate");
        System.out.println(" 3 - Admin");
        System.out.println(" 0 - Exit");
    }

    public void meniuAngajat() {
        while(true){
            printMeniuAngajat();
            int option;
            while(true){
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if(option >= 0 && option <=3) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 3");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 3");
                }
            }
            if(option == 1){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 2){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 3){
                meniuAdmin();
            }
            else if(option == 0){
                break;
            }
        }
    }

    public void meniuAdmin() {
        while(true){
            // print Meniu Admin
            System.out.println();
            System.out.println("Ce categorie doriti sa modificati?");
            System.out.println(" 1 - Angajati");
            System.out.println(" 2 - Bilete");
            System.out.println(" 3 - Tururi");
            System.out.println(" 4 - Expozitii");
            System.out.println(" 5 - Galerii");
            System.out.println(" 6 - Exponate");
            System.out.println(" 0 - Exit");

            int option;
            while(true){
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if(option >= 0 && option <=6) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 6");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 6");
                }
            }
            if(option == 1){
                CRUD_Angajati();
            }
            else if(option == 2){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 3){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 4){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 5){
                System.out.println("Urmeaza a fi implementat");
            }
            else if(option == 6){
                System.out.println("Urmeaza a fi implementat");
            }

            else if(option == 0){
                break;
            }
        }
    }

    public void printOptions(){
        System.out.println();
        System.out.println(" 1 - Get All");
        System.out.println(" 2 - Get By Id");
        System.out.println(" 3 - Add");
        System.out.println(" 4 - Update");
        System.out.println(" 5 - Delete");
        System.out.println(" 0 - Exit");
    }

    public void CRUD_Angajati(){
        while(true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1) {

                if(service_angajat.getAngajati().size() == 0){
                    System.out.println("Nu exista angajati!");
                }
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    System.out.println(service_angajat.getAngajati().get(i).toString());
                }
            }
            else if(option == 2) {

                int index;
                while(true){
                    System.out.println("Introduceti id-ul angajatului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if(service_angajat.getAngajati().size() == 0){
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); ++i) {
                    if(service_angajat.getAngajati().get(i).getID_ang() == index){
                        ok = true;
                        break;
                    }
                }
                if(ok){
                    System.out.println(service_angajat.getAngajatById(index).toString());
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            }
            else if (option == 3) {

                Angajat angajat = service_angajat.readAngajat();
                service_angajat.addAngajat(angajat);
            }
            else if (option == 4) {

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul angajatului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    if (service_angajat.getAngajati().get(i).getID_ang() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Angajat angajat = service_angajat.readAngajat();
                    angajat.setID_ang(index);
                    service_angajat.updateAngajat(index, angajat);
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            }
            else if(option == 5){

                int index;
                while(true){
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if(service_angajat.getAngajati().size() == 0){
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    if(service_angajat.getAngajati().get(i).getID_ang() == index){
                        ok = true;
                        break;
                    }
                }
                if(ok){
                    service_angajat.deleteAngajatById(index);
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            }
            else if (option == 0) {
                break;
            }

        }
    }



}
