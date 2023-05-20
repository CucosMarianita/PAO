package org.example;

import Service.Service;
import entities.Tur;

import java.sql.SQLException;
import java.text.ParseException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws ParseException, IOException, SQLException {
        Service service = Service.getInstance();
        service.meniu();
    }
}