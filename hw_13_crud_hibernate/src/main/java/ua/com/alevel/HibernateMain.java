package ua.com.alevel;

import ua.com.alevel.controller.impl.CrudControllerImpl;

import java.io.IOException;

public class HibernateMain {
    public static void main(String[] args) throws IOException {
        CrudControllerImpl crud = new CrudControllerImpl();
        crud.start();

    }
}