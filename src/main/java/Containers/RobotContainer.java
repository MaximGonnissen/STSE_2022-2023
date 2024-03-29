package Containers;


import WarehouseRobot.RobComponents;
import WarehouseRobot.RobotInformation;

/**
 * This is a simple container class. It creates a container for a single agent, to be deployed on a single machine.
 * The final version of the project will have multiple agents per machine, and multiple machines.
 * This is primarily for testing and early development purposes.
 *
 * @author Maxim
 * @author Thimoty
 * @author Senne
 * @author Anthony
 * @version 1.0
 * @see BaseContainer
 * @see jade.util.ExtendedProperties
 * @see jade.core.Profile
 * @since 02/11/2022
 */
public class RobotContainer extends BaseContainer {
    public static void main(String[] args) {
        RobotContainer robotContainer = new RobotContainer();
        robotContainer.setConfigFromPath("./configs/robotcontainer.json");
        robotContainer.start();
    }

    @Override
    protected void createAgents() {
        RobComponents.init();
        super.createAgents();
        RobotInformation.uwbID = config.get("uwb_id").getAsString();
    }

    @Override
    protected void createContainer() {
        agentContainer = runtime.createAgentContainer(profile);
    }
}
