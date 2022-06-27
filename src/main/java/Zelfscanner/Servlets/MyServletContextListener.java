package Zelfscanner.Servlets;

import Zelfscanner.Persistentie.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PersistenceManager.loadWinkelFromAzure();
            System.out.println("De winkel is ingeladen");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveWinkelToAzure();
            System.out.println("De winkel is geupload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
