package Containers;
import WarehouseServer.LocationManager;
import jade.core.Profile;
import jade.util.ExtendedProperties;

import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;

/**
 * This is the main container class. It is responsible for creating the main container for our system.
 * It essentially serves as the "server" for our system.
 *
 * @author Maxim
 * @author Thimoty
 * @author Senne
 * @author Anthony
 * @version 1.0
 * @see BaseContainer
 * @since 02/11/2022
 */
public class MainContainer extends BaseContainer {
    public static void main(String[] args) throws Exception {
        MainContainer mainContainer = new MainContainer();
        mainContainer.setConfigFromPath("./configs/maincontainer.json");
        mainContainer.start();
    }

    @Override
    protected void preAgents() {
        super.preAgents();
        LocationManager.createLocations();
    }
}
