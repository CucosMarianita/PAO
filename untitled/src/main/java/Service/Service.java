package Service;

import entities.*;

import java.util.*;
import java.io.IOException;

public class Service {

    private final AngajatService service_angajat = AngajatService.getInstance();
    private final BiletService service_bilet = BiletService.getInstance();
    private final TipBiletService service_tip_bilet = TipBiletService.getInstance();
    private final TurService service_tur = TurService.getInstance();
    private final ExpozitieService service_expozitie = ExpozitieService.getInstance();
    private final GalerieService service_galerie = GalerieService.getInstance();
    private final TablouService service_tablou = TablouService.getInstance();
    private final SculpturaService service_sculptura = SculpturaService.getInstance();
    private final RelicvaService service_relicva = RelicvaService.getInstance();
    private final MuzeuService service_muzeu = MuzeuService.getInstance();


    private static Service instance;
    private Scanner scanner = new Scanner(System.in);

    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void printUserMenu() {
        System.out.println(" 1 - Vizitator");
        System.out.println(" 2 - Angajat");
        System.out.println(" 0 - Exit");
    }

    public void meniu() throws IOException {
        while (true) {
            printUserMenu();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 2) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 2");
                    }
                } catch (Exception e) {
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

    public void printMeniuVizitator() {
        System.out.println();
        System.out.println(" 1 - Rezervare bilet");
        System.out.println(" 2 - Afisare program muzeu");
        System.out.println(" 3 - Afisare tururi");
        System.out.println(" 4 - Afisare expozitii");
        System.out.println(" 0 - Exit");

    }

    public void meniuVizitator() {
        while (true) {
            printMeniuVizitator();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 4) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 4");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 4");
                }
            }

            if (option == 1) {          // rezervare bilet
                Bilet achizitie = service_bilet.readBilet();
                service_bilet.addBilet(achizitie);

            } else if (option == 2) {   // afisare program muzeu
                Muzeu m = service_muzeu.getMuzeu();
                System.out.println(m.toString());

            } else if (option == 3) {  // afisare tururi
                List<Tur> tururi = service_tur.getTururi();
                System.out.println(tururi);

            } else if (option == 4) {  // afisare expozitii
                List<Expozitie> exp = service_expozitie.getExpozitii();
                System.out.println(exp);

            } else
                break;
        }
    }

    public void printMeniuAngajat() {
        System.out.println();
        System.out.println(" 1 - Afisare numar vizitatori");
        System.out.println(" 2 - Sortare exponate");
        System.out.println(" 3 - Admin");
        System.out.println(" 0 - Exit");
    }

    public void meniuAngajat() {
        while (true) {
            printMeniuAngajat();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 3) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 3");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 3");
                }
            }

            if (option == 1) {
                System.out.println("Introduceti numarul de luni pentru care doriti sa afisati numarul de vizitatori");
                int nr_luni = scanner.nextInt();
                System.out.println("Numarul de bilete vandute in ultimele "+ nr_luni +" luni este:");
                List<Bilet> bilete = service_bilet.getBiletePerioada(nr_luni);
                System.out.println(bilete.size());

            } else if (option == 2) {
                System.out.println("Tablouri (sortare dupa stil, an si id):c");
                System.out.println(service_tablou.getTablouri());       // sortare dupa stil si an

                System.out.println("Sculpturi (sortare dupa stil si id):");
                System.out.println(service_sculptura.getSculpturi());   // sortare dupa stil si id

                System.out.println("Relicve (sortare dupa an si id):");
                System.out.println(service_relicva.getRelicve());       // sortare dupa an si id

            } else if (option == 3) {
                meniuAdmin();

            } else
                break;
        }
    }

    public void meniuAdmin() {
        while (true) {
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
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 6");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 6");
                }
            }
            if (option == 1) {
                CRUD_Angajati();
            } else if (option == 2) {
                CRUD_Bilete();
            } else if (option == 3) {
                CRUD_Tururi();
            } else if (option == 4) {
                CRUD_Expozitii();
            } else if (option == 5) {
                CRUD_Galerii();
            } else if (option == 6) {
                Meniu_exponate();
            } else if (option == 0) {
                break;
            }
        }
    }

    public void Meniu_exponate(){
        while (true) {
            System.out.println();
            System.out.println("Ce exponate doriti sa modificati?");
            System.out.println(" 1 - Tablouri");
            System.out.println(" 2 - Sculpturi");
            System.out.println(" 3 - Relicve");
            System.out.println(" 0 - Exit");

            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 3) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 3");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 3");
                }
            }
            if (option == 1) {
                CRUD_Tablouri();
            } else if (option == 2) {
                CRUD_Sculpturi();
            } else if (option == 3) {
                CRUD_Relicve();
            } else if (option == 0) {
                break;
            }
        }
    }

    public void printOptions() {
        System.out.println();
        System.out.println(" 1 - Get All");
        System.out.println(" 2 - Get By Id");
        System.out.println(" 3 - Add");
        System.out.println(" 4 - Update");
        System.out.println(" 5 - Delete");
        System.out.println(" 0 - Exit");
    }

    public void CRUD_Angajati() {
        while (true) {
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
            if (option == 1) {  // get all

                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    System.out.println(service_angajat.getAngajati().get(i).toString());
                }
            } else if (option == 2) {  // get by id

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
                for (int i = 0; i < service_angajat.getAngajati().size(); ++i) {
                    if (service_angajat.getAngajati().get(i).getID_ang() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_angajat.getAngajatById(index).toString());
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }

            } else if (option == 3) {  // add

                Angajat angajat = service_angajat.readAngajat();
                service_angajat.addAngajat(angajat);

            } else if (option == 4) {  // update

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
            } else if (option == 5) {  // delete

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
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
                    service_angajat.deleteAngajatById(index);
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            } else if (option == 0) {  // exit
                break;
            }

        }
    }

    public void CRUD_Bilete() {
        while (true) {
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
            if (option == 1) {   // get all

                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    System.out.println(service_tip_bilet.getBilete().get(i).toString());
                }
            } else if (option == 2) {    // get by id

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); ++i) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_tip_bilet.getBiletById(index).toString());
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 3) {  // add

                Bilet bilet = service_tip_bilet.readBilet();
                service_tip_bilet.addBilet(bilet);

            } else if (option == 4) {  // update

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Bilet bilet = service_tip_bilet.readBilet();
                    bilet.setID_bilet(index);
                    service_tip_bilet.updateBilet(index, bilet);
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 5) {  // delete

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_tip_bilet.deleteBiletById(index);
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 0) {
                break;
            }
        }

    }

    public void CRUD_Tururi() {
        while (true) {
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
            if (option == 1) {   // get all

                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    System.out.println(service_tur.getTururi().get(i).toString());
                }
            } else if (option == 2) {    // get by id

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); ++i) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_tur.getTurById(index).toString());
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 3) {  // add

                Tur tur = service_tur.readTur();
                service_tur.addTur(tur);

            } else if (option == 4) {  // update

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Tur tur = service_tur.readTur();
                    tur.setID_tur(index);
                    service_tur.updateTur(index, tur);
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 5) {  // delete

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_tur.deleteTurById(index);
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Expozitii(){
        while (true) {
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
            if (option == 1) {   // get all

                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    System.out.println(service_expozitie.getExpozitii().get(i).toString());
                }
            } else if (option == 2) { // get by id
                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); ++i) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_expozitie.getExpozitieById(index).toString());
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }

            } else if (option == 3) {  // add

                Expozitie expozitie = service_expozitie.readExpozitie();
                service_expozitie.addExpozitie(expozitie);

            } else if (option == 4) {  // update

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Expozitie expozitie = service_expozitie.readExpozitie();
                    expozitie.setID_expozitie(index);
                    service_expozitie.updateExpozitie(index, expozitie);
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }

            } else if (option == 5) { // delete
                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_expozitie.deleteExpozitieById(index);
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Galerii(){
        while (true) {
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
            if (option == 1){  // get all
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    System.out.println(service_galerie.getGalerii().get(i).toString());
                }

            } else if (option == 2) { // get by id

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); ++i) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_galerie.getGalerieById(index).toString());
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }

            } else if (option == 4){  // update

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Galerie galerie = service_galerie.readGalerie();
                    galerie.setID_galerie(index);
                    service_galerie.updateGalerie(index, galerie);
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }
            } else if (option == 5){  // delete

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_galerie.deleteGalerieById(index);
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Tablouri(){
        while(true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1){   // get all
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    System.out.println(service_tablou.getTablouri().get(i).toString());
                }
            } else if (option == 2){  // get by id

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); ++i){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_tablou.getTablouById(index).toString());
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }

            } else if(option == 3){   // add
                Tablou tablou = service_tablou.readTablou();
                service_tablou.addTablou(tablou);

            } else if (option == 4){   // update

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Tablou tablou = service_tablou.readTablou();
                    tablou.setID_exponat(index);
                    service_tablou.updateTablou(index, tablou);
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }

            } else if (option == 5){   // delete

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_tablou.deleteTablouById(index);
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }
            } else if (option == 0){   // exit
                break;
            }
        }
    }

    public void CRUD_Sculpturi(){
        while (true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }

            if (option == 1){
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    System.out.println(service_sculptura.getSculpturi().get(i).toString());
                }
            }
            else if (option == 2){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); ++i){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_sculptura.getSculpturaById(index).toString());
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }
            else if (option == 3){
                Sculptura sculptura = service_sculptura.readSculptura();
                service_sculptura.addSculptura(sculptura);
            }
            else if (option == 4){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Sculptura sculptura = service_sculptura.readSculptura();
                    sculptura.setID_exponat(index);
                    service_sculptura.updateSculptura(index, sculptura);
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }
            else if (option == 5){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_sculptura.deleteSculpturaById(index);
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }else if (option == 0){
                break;
            }
        }
    }

    public void CRUD_Relicve(){
        while(true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }

            if (option == 1){
                if(service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    System.out.println(service_relicva.getRelicve().get(i).toString());
                }
            }
            else if (option == 2){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); ++i){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_relicva.getRelicvaById(index).toString());
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            } else if (option == 3) {
                Relicva relicva = service_relicva.readRelicva();
                service_relicva.addRelicva(relicva);

            }else if (option == 4){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Relicva relicva = service_relicva.readRelicva();
                    relicva.setID_exponat(index);
                    service_relicva.updateRelicva(index, relicva);
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            }else if (option == 5){
                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_relicva.deleteRelicvaById(index);
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            } else if (option == 0){
                break;
            }
        }
    }

}
