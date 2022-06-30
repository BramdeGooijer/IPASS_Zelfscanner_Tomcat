package Zelfscanner.Servlets;

import Zelfscanner.Persistentie.PersistenceManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;

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

            Schedulers.shutdownNow();
            HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();

            System.out.println("De winkel is geupload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
