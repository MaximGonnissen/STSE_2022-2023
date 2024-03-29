package Agents;

import Behaviours.CollisionAvoidanceCheckBehaviour;
import Behaviours.GeneralServerBehaviour;
import Behaviours.JobGeneratorBehaviour;
import Behaviours.ServerMessageParserBehaviour;
import Behaviours.UWBReceivingBehaviour;
import WarehouseServer.JobStorage;
import WarehouseShared.Config;
import WarehouseShared.Job;
import WarehouseShared.Position;
import com.google.gson.JsonArray;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

/**
 * This agent is used for the server.
 *
 * @author Maxim
 * @author Thimoty
 * @version 1.0
 * @see ServerMessageParserBehaviour
 * @since 26/11/2022
 */
public class ServerAgent extends Agent {
    private final ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();
    @Override
    protected void setup() {
        Behaviour serverMessageParserBehaviour = new ServerMessageParserBehaviour();
        Behaviour uwbReceivingBehaviour = new UWBReceivingBehaviour();
        Behaviour generalServerBehaviour = new GeneralServerBehaviour();
        Behaviour collisionAvoidanceCheckBehaviour = new CollisionAvoidanceCheckBehaviour();
        addBehaviour(tbf.wrap(serverMessageParserBehaviour));
        addBehaviour(tbf.wrap(uwbReceivingBehaviour));
        addBehaviour(tbf.wrap(generalServerBehaviour));
        addBehaviour(tbf.wrap(collisionAvoidanceCheckBehaviour));

        if (Config.getConfig().get("useJobGenerator").getAsBoolean()) {
            Behaviour jobGeneratorBehaviour = new JobGeneratorBehaviour();
            addBehaviour(tbf.wrap(jobGeneratorBehaviour));
        }
    }
}
